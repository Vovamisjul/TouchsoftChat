package com.olrox.chat.user;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThread extends Thread{
    private PrintWriter writer;
    private Socket socket;
    private Connection connection;

    public WriteThread(Socket socket, Connection connection) {
        this.socket = socket;
        this.connection = connection;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        Console console = System.console();

        String text;

        while (true) {
            text = console.readLine();

            if(text.equals("/exit")) {
                break;
            }

            writer.println(text);
        }

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}
