import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Network2 {
    private String ip = "localhost";
    private int port = 22222;
    private Scanner scanner = new Scanner(System.in);

    private Thread thread;
    private Thread thread22;
    private Socket clientSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket serverSocket;

    private boolean isHost = false;
    private boolean yourTurn = false;
    private boolean clientAccepted = false;
    private boolean isConnected = false;
    private int playerID;

    private Bike yourBike;
    private Bike enemyBike;

    private final SendData sendData;
    private final GetData getData;

    //To remove later:
    private String[] spaces = new String[9];

    public Network2(){

        // Ask what IP/Port you want to connect to
        /*
        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        System.out.println("Please input the port: ");
        port = scanner.nextInt();
        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }
        */
        while (!isConnected && !isHost) {
            System.out.println("Do you want to host game? (Y/N)");
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("y")) {
                System.out.println("Setting up server...");
                this.initializeServer();
                System.out.println("Server set up, waiting for client connection...");
            } else {
                System.out.println("Connecting to server...");
                this.connect();
            }
        }
        Scanner scanner = new Scanner(System.in);
        yourBike = new Bike(playerID);
        System.out.println("podaj x:");
        yourBike.setPosX(scanner.nextInt());
        System.out.println("podaj y:");
        yourBike.setPosY(scanner.nextInt());

        if (isHost){
            this.listenForServerRequest();
        }

        sendData = new SendData(this.oos);
        getData = new GetData(this.ois);

        thread = new Thread(sendData);
        thread22 = new Thread(getData);
        thread.start();
        thread22.start();
    }

    private void connect() {
        try {
            clientSocket = new Socket(ip, port);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Successfully connected to the server.");
            playerID = 1;
            isConnected = true;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port);
        }
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            isHost = true;
            yourTurn = true;
            playerID = 0;
            clientAccepted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listenForServerRequest() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            clientAccepted = true;
            System.out.println("Successfully accepted client request.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
