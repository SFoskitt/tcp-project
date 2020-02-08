


Overview:
The server opens a TCP connection on a port
The client opens a TCP connection on the same port and confirms the connection
The client writes to terminal the list of optional commands
From the client send "stop" to close the client and stop the server

-- source files
  TCPClient.java 
  TCPServer.java
  can be compiled with
    `javac TCPClient.java`
    `javac TCPServer.java`
  produces corresponding *.class files

-- class files
  TCPClient.class
  TCPServer.class

-- how to run
  -- server
    1. in a terminal run `java TCPServer`
    2. in a separate terminal run `java TCPClient`
    3. Client writes instructions to termainal


  -- client

