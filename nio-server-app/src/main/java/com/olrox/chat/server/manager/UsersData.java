package com.olrox.chat.server.manager;

import com.olrox.chat.server.exception.InvalidUserStateException;
import com.olrox.chat.server.user.User;
import com.olrox.chat.server.user.state.FreeAgentState;
import com.olrox.chat.server.user.state.FreeClientState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class UsersData {

    private final static Logger LOGGER = LogManager.getLogger(UsersData.class);

    private final Queue<User> freeClients = new ArrayDeque<>();
    private final Queue<User> freeAgents = new ArrayDeque<>();
    private final Set<String> onlineUsers = new HashSet<>();

    private synchronized void removeOfflineClients(){
        User freeClient = freeClients.peek();
        while(freeClient != null && !onlineUsers.contains(freeClient.getUsername())){
            freeClients.poll();
            freeClient = freeClients.peek();
        }
    }

    private synchronized void removeOfflineAgents(){
        User freeAgent = freeAgents.peek();
        while(freeAgent != null && !onlineUsers.contains(freeAgent.getUsername())){
            freeAgents.poll();
            freeAgent = freeAgents.peek();
        }
    }

    public synchronized boolean hasFreeClient(){
        LOGGER.debug("Free clients: " + freeClients);

        removeOfflineClients();
        return !freeClients.isEmpty();
    }

    public synchronized boolean hasFreeAgent(){
        LOGGER.debug("Free agents: " + freeAgents);

        removeOfflineAgents();
        return !freeAgents.isEmpty();
    }

    public synchronized User pollFreeClient(){
        LOGGER.debug("Free client was removed.");

        return freeClients.poll();
    }

    public synchronized User pollFreeAgent(){
        LOGGER.debug("Free agent was removed");

        return freeAgents.poll();
    }

    public synchronized void addFreeClient(User client){
        if(!(client.getState() instanceof FreeClientState)) {
            throw new InvalidUserStateException("User isn't in FreeClientState.");
        }
        freeClients.add(client);

        LOGGER.debug("Free client " + client.getUsername() + " was added.");
        LOGGER.debug("Free clients: " + freeClients);
    }

    public synchronized void addFreeAgent(User agent) {
        if(!(agent.getState() instanceof FreeAgentState)) {
            throw new InvalidUserStateException("User isn't in FreeAgentState.");
        }
        freeAgents.add(agent);

        LOGGER.debug("Free agent " + agent.getUsername() + " was added.");
        LOGGER.debug("Free agents: " + freeAgents);
    }

    public synchronized boolean addOnlineUser(String username) {
        boolean isNewUser = onlineUsers.add(username);

        if(isNewUser) {
            LOGGER.debug("Online user " + username + " was added: " + onlineUsers);
        } else {
            LOGGER.debug("Online user " + username + " already registered: " + onlineUsers);
        }

        return isNewUser;
    }

    public synchronized void removeOnlineUser(String username) {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onlineUsers.remove(username);

        LOGGER.debug("Online user " + username + " was removed: " + onlineUsers);
    }
}
