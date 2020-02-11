import java.io.*;
import java.net.*;

class TCPClient {
 public static void main(String argv[]) throws Exception {
    String command = "";
    Socket clientSocket = new Socket("localhost", 6790);

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Client started: " + clientSocket.getPort());

    System.out.print("" +
        "0. Stop the client and close the server.\n" +
        "1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.\n" +
        "2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.\n" +
        "3. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.\n" +
        "4. display_all: this request displays the information of all the students currently in the database.\n" +
        "5. delete(ID): this request deletes the student entry with that ID.\n\n"
    );
    System.out.println("Student record: (whole number positive integer ID) (string FirstName) (string LastName) (whole number positive Score)\n");
    System.out.println("Separate values by a single space\n\n");

    while (!command.equals("0")) {
        System.out.print("Enter your command choice: ");

        command = inFromUser.readLine();
        if(!validateIntegerEntry(command)){
            System.out.println("command is not an integer\n");
            command = "";
        }

        switch(command) {
            case "0":
                System.out.println("Client stopping...");
                closeServer(clientSocket, "0stop");
                break;

            case "1":
                System.out.print("Enter student record: ID FName LName Score \n");
                String case1data = inFromUser.readLine();
                if (validateStudentRecord(case1data)){
                    command += case1data;
                    sendToServer(clientSocket, command);
                } else {
                    System.out.println("That doesn't work, try again");
                }
                break;

            case "2":
                System.out.print("Enter whole number student ID (integer) to display: ");
                String case2data = inFromUser.readLine();
                if (validateIntegerEntry(case2data)){
                    command += case2data;
                    sendToServer(clientSocket, command);
                }
                break;

            case "3":
                System.out.print("Enter whole number score (integer) to search: ");
                String case3data = inFromUser.readLine();
                if (validateIntegerEntry(case3data)) {
                    command += case3data;
                    sendToServer(clientSocket, command);
                }
                break;

            case "4":
                System.out.print("Display *ALL* records? (Y/N): ");
                String case4data = inFromUser.readLine();
                if (case4data.equalsIgnoreCase("y")) {
                    sendToServer(clientSocket, command);
                }
                break;

            case "5":
                System.out.print("Enter student ID (integer) to DELETE!! ");
                String case5data = inFromUser.readLine();
                if (validateIntegerEntry(case5data)) {
                    System.out.println("Are you sure you want to DELETE record for ID = " + case5data + " (y/n)?");
                    String verify = inFromUser.readLine();
                    if (verify.equalsIgnoreCase("y")) {
                        command += case5data;
                        sendToServer(clientSocket, command);
                    }
                }
                break;

            default:
                System.out.println("That command was not clear at all, please try again.");
        }
    }
  }

    static private Boolean validateStudentRecord(String data) {
        String[] dataParsed = data.split(" ");

        if (validateIntegerEntry(dataParsed[0]) && validateIntegerEntry(dataParsed[3])) {
            return true;
        }
        return false;
    }

    static private Boolean validateIntegerEntry(String data) {
        try {
            int entry = Integer.parseInt(data);
            if (entry >= 0 && entry % 1 == 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Validate integer exception: " + e);
            return false;
        }
        System.out.println("Value is not an integer");
        return false;
    }

    static private void sendToServer(Socket clientSocket, String command) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

        outToServer.writeUTF(command);
        String serverResponse;
        while((serverResponse = inFromServer.readUTF()) == null){
        }
        System.out.println("RESPONSE FROM SERVER:\n" + serverResponse); // inFromServer.readUTF()
    }

    static private void closeServer(Socket clientSocket, String command) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeUTF(command);
        outToServer.close();
    }
}


// 1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.

// 2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.

// 2. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.

// 3. display_all: this request displays the information of all the students currently in the database.

// 4. delete(ID): this request deletes the student entry with that ID.
