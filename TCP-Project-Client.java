import java.io.*;
import java.net.*;

class TCPProjectClient {
 public static void main(String argv[]) throws Exception {
    String command = "";
    String serverReply = "";
    Socket clientSocket = new Socket("localhost", 6789);

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("client socket is open? " + clientSocket.getPort());

    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

    while (!command.equals("stop")) {
        System.out.println("" +
                "1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.\n" +
                "2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.\n" +
                "3. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.\n" +
                "4. display_all: this request displays the information of all the students currently in the database.\n" +
                "5. delete(ID): this request deletes the student entry with that ID.\n" +
        "")
        System.out.print("Enter your command choice ('Stop' to end): ");
        command = inFromUser.readLine();

        // FOR ALL OF THESE OPEN, READ, CLOSE NEW DATASTREAM FOR THE SUBSEQUENT INFORMATION
        switch(command.toLowerCase()) {
            case "1":
                System.out.print("Enter student record as: (integer ID) (string FirstName) (string LastName) (integer Score): ");
                // DataInputStream data = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                // data parse
                // data validation and error message - break switch on error
                break;
            case "2":
                System.out.print("Enter student ID (integer) to display: ");
                // data validataion (integer only) - break switch on error
                break;
            case "3":
                System.out.print("Enter score (integer) to search: ");
                // data validation (int only) - break switch on error
                break;
            case "4":
                System.out.print("Display ALL records? (Y/N): ");
                // if Y then send
                // if N then break switch
                break;
            case "5":
                System.out.print("Enter student ID (integer) to DELETE!! ");
                // data validation (int only) - break switch on error
                // VERIFY delete student ID record??
                break;
            case "stop":
                // verify really quit then set "message" = "stop"
                break;
            default:
                System.out.println("That command was not clear at all, please try again.");
        }
        // IN ALL CASES WE'RE SENDING SOMETHING TO THE SERVER
        outToServer.writeUTF(command); // change this to "message" when data is formatted to send
        serverReply = inFromServer.readUTF();
        System.out.println("FROM SERVER: " + serverReply);
    }

    outToServer.close();
    clientSocket.close();
  }
}


// 1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.

// 2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.

// 2. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.

// 3. display_all: this request displays the information of all the students currently in the database.

// 4. delete(ID): this request deletes the student entry with that ID.


// // A Java program for a Client
// import java.net.*;
// import java.io.*;

// public class Client
// {
//     // initialize socket and input output streams
//     private Socket socket            = null;
//     private DataInputStream  input   = null;
//     private DataOutputStream out     = null;

//     // constructor to put ip address and port
//     public Client(String address, int port)
//     {
//         // establish a connection
//         try
//         {
//             socket = new Socket(address, port);
//             System.out.println("Connected");

//             // takes input from terminal
//             input  = new DataInputStream(System.in);

//             // sends output to the socket
//             out    = new DataOutputStream(socket.getOutputStream());
//         }
//         catch(UnknownHostException u)
//         {
//             System.out.println(u);
//         }
//         catch(IOException i)
//         {
//             System.out.println(i);
//         }

//         // string to read message from input
//         String line = "";

//         // keep reading until "Over" is input
//         while (!line.equals("Over"))
//         {
//             try
//             {
//                 line = input.readLine();
//                 out.writeUTF(line);
//             }
//             catch(IOException i)
//             {
//                 System.out.println(i);
//             }
//         }

//         // close the connection
//         try
//         {
//             input.close();
//             out.close();
//             socket.close();
//         }
//         catch(IOException i)
//         {
//             System.out.println(i);
//         }
//     }

//     public static void main(String args[])
//     {
//         Client client = new Client("127.0.0.1", 5000);
//     }
// }