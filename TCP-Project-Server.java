import java.io.*;
import java.net.*;

class TCPProjectServer {
    public static void main(String argv[]) throws Exception {
        String clientSentence = "";
        ServerSocket server = new ServerSocket(6789);
        Socket connectionSocket = server.accept();
        System.out.println("Server initiated at port: " + server.getLocalPort());

        DataInputStream inFromClient = new DataInputStream(new BufferedInputStream(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        while (!clientSentence.toLowerCase().equals("stop")) {
            System.out.println("Connection socket open: " + connectionSocket.getLocalPort());

            clientSentence = inFromClient.readUTF();
            System.out.println("Message received: " + clientSentence);

            // need a model?
            switch (clientSentence) {
                case "1":
                    // add new record - develop record - append to file - spit out single record - errors for missing values
                case "2":
                    // display record by id - iterate until record id - spit out single record
                case "3":
                    // display all records above the sent score. - setup sub route for displaying multiple records
                case "4":
                    // display all records - setup sub routine for displaying multiple records
                case "5":
                    // delete record by id - find the record - echo back the record - delete - save file
            }

            outToClient.writeUTF("SERVER REPLY HERE");
        }

        inFromClient.close();
        outToClient.close();
        server.close();
    }
}


// 1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.

// 2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.

// 2. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.

// 3. display_all: this request displays the information of all the students currently in the database.

// 4. delete(ID): this request deletes the student entry with that ID.

// // A Java program for a Server
// import java.net.*;
// import java.io.*;

// public class Server
// {
//     //initialize socket and input stream
//     private Socket          socket   = null;
//     private ServerSocket    server   = null;
//     private DataInputStream in       =  null;

//     // constructor with port
//     public Server(int port)
//     {
//         // starts server and waits for a connection
//         try
//         {
//             server = new ServerSocket(port);
//             System.out.println("Server started");

//             System.out.println("Waiting for a client ...");

//             socket = server.accept();
//             System.out.println("Client accepted");

//             // takes input from the client socket
//             in = new DataInputStream(
//                 new BufferedInputStream(socket.getInputStream()));

//             String line = "";

//             // reads message from client until "Over" is sent
//             while (!line.equals("Over"))
//             {
//                 try
//                 {
//                     line = in.readUTF();
//                     System.out.println(line);

//                 }
//                 catch(IOException i)
//                 {
//                     System.out.println(i);
//                 }
//             }
//             System.out.println("Closing connection");

//             // close connection
//             socket.close();
//             in.close();
//         }
//         catch(IOException i)
//         {
//             System.out.println(i);
//         }
//     }

//     public static void main(String args[])
//     {
//         Server server = new Server(5000);
//     }
// }
