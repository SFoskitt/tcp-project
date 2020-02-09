PLEASE NOTE:  The project description includes instructions for FIVE commands but are numbered: 1, 2, 2, 3, 4.  So to prevent confusion I'm making this note as a heads up.  My client and my server both take six commands - the five required plus a "stop" command.


Overview:
The server opens a TCP connection on a port
The client opens a TCP connection on the same port
The server confirms the connection
The client writes to terminal the list of optional commands
From the client send "stop" to close the client and stop the server

-- source files
    TCPClient.java
    TCPServer.java
  can be compiled with
    `javac TCPClient.java`
    `javac TCPServer.java`
  produces corresponding *.class files
    TCPClient.class
    TCPServer.class

-- how to run
  -- server
    1. in a new terminal run `java TCPServer`

  -- client
    1. in a separate new terminal run `java TCPClient`
    2. Client writes instructions to user to the termainal

