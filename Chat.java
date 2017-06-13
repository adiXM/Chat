/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;
import java.io.*; //importam librariile necesare
import java.net.*;
 
/**
 *
 * @author Adrian
 */
public class Chat {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket s = null; // socketul pentru server
        Socket conn = null; ///pentru client
        PrintStream out = null; //output
        BufferedReader in = null; //input
        String message = null; ///mesaj
        
        try
        {
            //1. Cream socketul
            s = new ServerSocket(5000 , 10);  ///port si numarul maxim de conexiuni in coada
             
            //2. Deschidere server pentru clienti
            echo("Server socket created.Waiting for connection..."); ///am deschis serverul
            
            conn = s.accept(); ///accepta noii clienti
            
            echo("Connection received from " + conn.getInetAddress().getHostName() + " : " + conn.getPort()); //Conexiune primita de la
             
            //3. Obtine input si output
            out = new PrintStream(conn.getOutputStream()); ///deschid output
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream())); ///deschid inputul
             
            out.println("Welcome. Server version 1.0"); ///Conexiune reusita la server
            out.flush();
 
 
 
 
            //4. The two parts communicate via the input and output streams
            do
            {
                
                message = (String)in.readLine(); //citire input client
                echo("Client>" + message); ///afiseaza mesajul clientului
                 
                if(message != null)
                {
                    //out.println(message);
                }
                else
                {
                    echo("Client has disconnected");
                    break;
                }
            }
            while(!message.equals("exit"));
        }
         
        catch(IOException e)
        {
            System.err.println("IOException"); ///gaseste niste erri
        }
         
        //Inchidem fisierele si socketul
        try
        {
            in.close();
            out.close();
            s.close();
        }
         
        catch(IOException ioException)
        {
            System.err.println("Unable to close. IOexception");
        }
                
    }
    public static void echo(String msg)
    {
        System.out.println(msg);
    }
    
}

