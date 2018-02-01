/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.*;

/**
 *
 * @author Abraham Ticona
 */
public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
        //Stream di byte di output 
        DataOutputStream out = null;
        //Stream di byte di input
        DataInputStream in = null;
        

        /**apertura della connessione al server sulla porta specificata
         * visto che il server si trova sulla stessa machhina remota si usa localhost
         * altrimenti bisogna conoscere l'indirizzo ip del server
        */
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            
            /**inializzazione dell'oggeto stream output
             * il DataOutputStream avrà nel suo costruttore un OutputStream
             * In questo caso i stream socket ossia getOutputStream
             */
            out = new DataOutputStream(connection.getOutputStream());
            //metodo che permette di scrivere una stringa nel byte usando la codifica UTF-8 
            out.writeUTF("Voglio la data");
            //metodo che serve nel pulire lo spazio utilizzato dal messagio inviato
            out.flush();
            //Messaggio inviaot al server
            
            //Inializzazione del stream input
            in = new DataInputStream(connection.getInputStream());
            //Messaggio inviato dal server 
            System.out.println("Server "+in.readUTF());
           //Il client leegge la risposta inviata dal server
        }
        
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }

        //chiusura della connnessione
        finally{
                try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
        }
    }
}
