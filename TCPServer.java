import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

class TCPServer {
    private static String FILE_NAME = "StudentData.txt";

    public static void main(String argv[]) throws Exception {
        System.out.println("Server starting...");

        String clientSentence = "";
        Boolean stop = false;

        ServerSocket server = new ServerSocket(6790);
        System.out.println("Server initiated at port: " + server.getLocalPort());

        Socket connectionSocket = server.accept();
        System.out.println("Client Socket: " + connectionSocket.getPort());

        DataInputStream inFromClient = new DataInputStream(new BufferedInputStream(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        while (!stop) {
            clientSentence = inFromClient.readUTF();
            System.out.println("Message received: " + clientSentence);

            String command = Character.toString(clientSentence.charAt(0));
            String data = clientSentence.substring(1);
// 1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.

// 2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.

// 2. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.

// 3. display_all: this request displays the information of all the students currently in the database.

// 4. delete(ID): this request deletes the student entry with that ID.

            switch (command) {
                case "0":
                    stop = true;
                    inFromClient.close();
                    outToClient.close();
                    break;
                case "1":
                    // add a new student's information into the database
                    outToClient.writeUTF("Adding new record: " + data);
                    try {
                        saveToDb(data);
                        outToClient.writeUTF("Record saved.");
                        printDb(outToClient);
                    } catch (IOException e) {
                        System.out.println(e);
                        outToClient.writeUTF("IO Exception: " + e);
                    }
                    break;
                case "2":
                    // display record by id
                    String record = getOneRecord(data);
                    if (record.charAt(0) == 'R') { // error message
                        System.out.println(data);
                        outToClient.writeUTF(data);
                    } else {
                        System.out.println("Request satisfied.");
                        outToClient.writeUTF("Requested record: " + record);
                    }
                    printDb(outToClient);
                    break;
                case "3":
                    // display all records above the sent score. - setup sub route for displaying
                case "4":
                    // display all records - setup sub routine for displaying multiple records
                    printDb(outToClient);
                case "5":
                    // delete record by id - find the record - echo back the record - delete - save
                    String deleted = deleteOneRecord(data);
                    if (deleted == "true"){
                        outToClient.writeUTF("Record " + data + " was deleted.");
                        printDb(outToClient);
                    }
            }
        }

        System.out.println("Server closing...");
        server.close();
    }

    private static String deleteOneRecord(String recordNum) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(FILE_NAME));
        char compare = recordNum.charAt(0);
        String remove = "";
        for(String line : fileLines) {
            char fileLineId = line.charAt(0);
            if(compare == fileLineId){
                remove = line;
            }
        }
        if (remove != "") {
            fileLines.remove(remove);
            Files.write(Paths.get(FILE_NAME), fileLines);
            return "true";
        }
        return "Record does not exist";
    }

    private static String getOneRecord(String recordNum) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(FILE_NAME));
        ListIterator<String> recordIterator = fileLines.listIterator();
        while(recordIterator.hasNext()){
            String tmp = recordIterator.next().toString();
            char compare = tmp.charAt(0);
            char record = recordNum.charAt(0);
            if (compare == record){
                return recordIterator.next().toString();
            }
        }
        return "Record does not exist";
    }

    private static void printDb(DataOutputStream outToClient) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(FILE_NAME));
        for(String line : fileLines) {
            System.out.println(line);
            outToClient.writeUTF(line);
        }
    }

    private static void saveToDb(String newData) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(FILE_NAME));
        fileLines.add(newData);
        Files.write(Paths.get(FILE_NAME), fileLines);
        System.out.println("Updated file content: ");
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
