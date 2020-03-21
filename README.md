## PLEASE NOTE: ## 
The project description includes instructions for FIVE commands but are numbered: 1, 2, 2, 3, 4.  So to prevent confusion I'm making this note as a heads up.  My client and my server both take six commands - the five required plus a "stop" command.


## OVERVIEW ##

- The server opens a TCP connection on a port
- The client opens a TCP connection on the same port
- The server confirms the connection
- The client writes to terminal the list of optional commands
- From the client send "0" to stop the client and close the server
- StudentData.txt comes pre-filled but can be blank when the server starts

### Source files ###
    TCPClient.java
    TCPServer.java

### Compile with CLI command ###
    javac TCPClient.java
    javac TCPServer.java

### Produces corresponding *.class files ###
    TCPClient.class
    TCPServer.class


## HOW TO RUN ##

  **FIRST** start the server **ON EROS ONLY**:

    1. in a new terminal ssh to EROS

    2. run `java TCPServer` (note: NOT `java TCPServer.class`)

    3. server echos client commands


  **SECOND** start the client on any other server:

    1. in a separate new terminal ssh to any other server (Zeus, Athena, Hercules)

    2. run `java TCPClient` (note: NOT `java TCPClient.class`)

    3. client writes instructions to user to the termainal

    4. enter 0 in client to stop client and close server


## CONTACT ME FOR QUESTIONS: ##

    sf1124@txstate.edu

    Stephanie Foskitt
