import java.io.*;
import java.net.*;

class TCPClient {
 public static void main(String argv[]) throws Exception {
    String command = "";
    Socket clientSocket = new Socket("localhost", 6790);

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Client started: " + clientSocket.getPort());

    while (!command.equals("0")) {
        System.out.print("" +
            "0. Stop the client and close the server." +
            "1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.\n" +
            "2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.\n" +
            "3. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.\n" +
            "4. display_all: this request displays the information of all the students currently in the database.\n" +
            "5. delete(ID): this request deletes the student entry with that ID.\n"
        );
        System.out.println("Student record: (integer ID) (string FirstName) (string LastName) (integer Score)");
        System.out.println("Separate values by a single space");
        System.out.println("");
        System.out.print("Enter your command choice: ");

        command = inFromUser.readLine();

        switch(command) {
            case "0":
                command += " stop";
                sendToServer(clientSocket, command);
                break;

            case "1":
                System.out.print("Enter student record: ID FName LName Score");
                String data = getData();
                if (validateStudentRecord(data)){
                    command += data;
                    sendToServer(clientSocket, command);
                } else {
                    System.out.println("That doesn't work, try again");
                }
                break;
            case "2":
                System.out.print("Enter student ID (integer) to display: ");
                String data = getData();
                if (validateIntegerEntry(data)){
                    command += data;
                    sendToServer(clientSocket, command);
                }
                break;
            case "3":
                System.out.print("Enter score (integer) to search: ");
                String data = getData();
                if (validateIntegerEntry(data)) {
                    command += data;
                    sendToServer(clientSocket, command);
                }
                break;
            case "4":
                System.out.print("Display *ALL* records? (Y/N): ");
                String data = getData();
                if (data.toLowerCase() == "y") {
                    sendToServer(clientSocket, command);
                }
                break;
            case "5":
                System.out.print("Enter student ID (integer) to DELETE!! ");
                String data = getData();
                if (validateIntegerEntry(data)) {
                    System.out.println("Are you sure you want to DELETE record for ID = " + data);
                    String verify = getData();
                    if (verify.toLowerCase() == "y") {
                        command += data;
                        sendToServer(clientSocket, command);
                    }
                }
                break;
            default:
                System.out.println("That command was not clear at all, please try again.");
        }
    }

    System.out.println("Client stopping...");
    inFromUser.close();
    clientSocket.close();
  }

    static private Boolean validateStudentRecord(String data) {
        String[] dataParsed = data.split(" ");

        try {
            Integer.parseInt(dataParsed[0]);
        } catch (NumberFormatException e) {
            System.out.println("Student ID must be integer");
            return false;
        }

        try {
            Integer.parseInt(dataParsed[3]);
        } catch (NumberFormatException e) {
            System.out.println("Grade value must be integer");
            return false;
        }

        return true;
    }

    static private Boolean validateIntegerEntry(String data) {
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException e) {
            System.out.println("Value is not an integer");
            return false;
        }
        return true;
    }

    static private void sendToServer(Socket clientSocket, String command) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

        outToServer.writeUTF(command);
        System.out.println("RESPONSE FROM SERVER: " + inFromServer.readUTF());
        outToServer.close();
    }

    static private String getData() throws IOException {
        BufferedReader dataFromUser = new BufferedReader(new InputStreamReader(System.in));
        String data = dataFromUser.readLine();
        dataFromUser.close();
        return data;
    }
}


// 1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.

// 2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.

// 2. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.

// 3. display_all: this request displays the information of all the students currently in the database.

// 4. delete(ID): this request deletes the student entry with that ID.
