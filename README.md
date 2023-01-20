# CORPORATE EVENT SYSTEM

### DESCRIPTION

The task is to build a system to maintain corporate events. The system should contain a database to store the data and
user interface, which will allow data manipulation.

### DATABASE

For this project H2 database will be used to store all the data.

### USER INTERFACE

1. User interface should allow execution of 9 main functionalities:
   * Login system.
   * Show information about an upcoming event.
   * Show history of any participated event.
   * Registration of a new participant.
   * Registration of a new user.
   * Chat with managers.
   * Data update.
   * Deleting information about an event.
   * Generating QR code based on user credentials.

2. The main view should allow us to display information about an event:
   * Photo of place where the event will occur.
   * Description of event.
   * Date of event.
   * Contact info.

### REQUIREMENTS
- [Node.js](https://nodejs.org/en/)

### RUNNING THE PROJECT
1. Clone the Git Repository
  ```bash
    git clone https://git.fdmgroup.com/Daniil.Zuyeu/fdmcorporateevent.git
 ```
1. Project Setup

#### Client
To get started, the next things need to be configured in [application.properties](src/main/resources/application.properties):
   * spring.datasource.url - 'dbc:h2:file:YOUR-PATH'
   * spring.sql.init.mode=always - uncomment and run the program, then comment it again.
   * spring.datasource.username= 'any username'
   * spring.datasource.password= 'any password'
   * spring.mail.username - 'any Gmail domain emails'
   * spring.mail.password - '[Generate app password](https://support.google.com/mail/answer/185833?hl=en)'

#### Server
1. Go to wss folder and type
```bash
   npm i wc
```

1. After that, run the server
```bash
   node index.js 
```
