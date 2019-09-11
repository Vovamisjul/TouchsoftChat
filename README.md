# TouchsoftChat

### Задание №1. Консольный чат.


#### Необходимый функционал:

- [X] Два типа пользователей: “клиент” и “агент”, которые могут общаться друг с другом.
- [X] Поддерживаются только личные сообщения клиента с агентом (клиент не может писать другому клиенту, а агент - другому агенту).
- [X] Когда клиент начинает писать сообщение, он ещё не знает, с каким агентом он будет общаться.
- [X] При получении первого сообщения клиента сервер должен выбрать свободного агента в качестве собеседника.
- [X] Агент может вести беседу одновременно только с одним клиентом.
- [X] Должны поддерживаться консольные команды для регистрации в системе, завершения чата и выходы из системы.
- [X] Сообщения, отправленные клиентом в отсутствии соединения с агентом не должны теряться.

#### Технические требования:
- [X] Автоматическая сборка проекта посредством Apache Maven
- [ ] Покрытие кода unit-тестами
- [X] Логирование основных событий с указанием времени:
    - [X] Появления агента и клиента в системе
    - [X] Начало и завершение чата
   
#### Пример варианта использования: 
- [X] Запускаем сервер
- [X] Запускаем клиентское приложение
- [X] Вводим “/register agent Cooper”
- [X] Сервер регистрирует нас как агента
- [X] Запускаем ещё один экземпляр клиентского приложения
- [X] Вводим “/register client Alice”
- [X] Сервер регистрирует нового клиента
- [X] Клиент вводит “Hello, I need help”
- [X] Агент получает сообщение и может ему ответить “How can I help you?”
- [X] Клиент может продолжить переписку или завершить чат: “/leave”
- [X] После завершения чата клиент может снова написать сообщение и, возможно, попасть на другого агента
- [X] Клиент или агент может выйти из системы, введя “/exit”, в этом случае приложение закрывается.

### Задание №2. Веб-интерфейс.

- [ ] Необходимо добавить веб-интерфейс для вашего агентского/клиентского приложения. Он должен быть представлен в виде веб-страницы со следующими элементами:
    - [ ] поле для ввода сообщений
    - [ ] поле для вывода сообщений
    - [ ] кнопка для отправки сообщений.
- [X] При этом, должна сохраняться обратная совместимость с вашим консольным приложением таким образом, чтобы консольный агент мог общаться с веб-клиентами и консольные клиенты могли общаться с веб-агентами.
