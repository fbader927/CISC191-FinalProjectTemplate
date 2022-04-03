package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;



//create abstract class to take in user input
abstract class customerInput {
    public void input() {
        //declare socket to enable communication between server and client
        Socket socket = null;
        //declare input and output streams to send messages between server/client
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        //declare buffers to read/write larger parts of data more efficiently
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            //declare socket object to communicate to server
            socket = new Socket("localhost", 4444);
            //connect input/output stream to socket
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            //wrap the input/output streams in buffer
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            //declare scanner for keyboard input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String userOrd = scanner.nextLine();
                //allow user input to buffer
                bufferedWriter.write(userOrd);
                bufferedWriter.newLine();
                //allows message to send after user enters input
                bufferedWriter.flush();
                //prints user input
                System.out.println("Server: " + bufferedReader.readLine());
                //breaks loop
                if (userOrd.equalsIgnoreCase("close"))
                    break;
            }
        }
        //error handler
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //required for error catching
            try {
                if (socket != null)
                    socket.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (outputStreamWriter != null)
                    outputStreamWriter.close();
                if (bufferedReader != null)
                    bufferedReader.close();
                if (bufferedWriter != null)
                    bufferedWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
//create class to extend abstract class (otherwise error)
class orders extends customerInput {
}
//create interface for welcome message
interface welcomePrompt {
}
//create class to implement the interface for the welcome message
class prompt implements welcomePrompt {
    public void welcome() {
        System.out.println("What would you like to order?\n");
    }
}
//create client class with main that creates the objects to display welcome prompt(interface) and take in user input(abstract class)
public class Client {
    public static void main(String[] args) {
        orders o = new orders();
        prompt p = new prompt();
        p.welcome();
        o.input();
    }
}




