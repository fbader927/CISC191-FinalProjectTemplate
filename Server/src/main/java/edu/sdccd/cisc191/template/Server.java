package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
Github release tag link for objective: https://github.com/MiramarCISC/CISC191-FinalProjectTemplate
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //declare socket for communication
        Socket socket = null;
        //declare input/output stream variables and buffer
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        //declare socket object and port for server to listen for connections
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(4444);

        while (true) {
            try {
                //declares socket object when connection to server is accepted
                socket = serverSocket.accept();
                //connect input/output stream to socket and declare buffer
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while(true) {
                    //gets user input and prints output
                    String ord = bufferedReader.readLine();
                    System.out.println("Customer: " + ord);
                    //states order was received
                    bufferedWriter.write("Order received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    //closes loop when statement is entered by user
                    if (ord.equalsIgnoreCase("close"));
                    break;
                }
                //closes everything when program ends
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            }
            //error handler
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
