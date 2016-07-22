package servicio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Worker implements Runnable
{
      private Socket client;

Worker(Socket client) 
{
   this.client = client;
}

public void run()
{

   DataInputStream dataClienteIn = null;
   DataOutputStream dataClienteOut = null;
   try
   {
      dataClienteIn = new DataInputStream(client.getInputStream()); //Obteno el input stream del socket cliente
      dataClienteOut = new DataOutputStream(client.getOutputStream()); //Obteno el output stream del socket cliente
   } catch (IOException e) {
     System.out.println("in or out failed");
     System.exit(-1);
   }
   try
   {
      String from = dataClienteIn.readUTF();
      String pass = dataClienteIn.readUTF();
      String to = dataClienteIn.readUTF();
      String subject = dataClienteIn.readUTF();
      String body = dataClienteIn.readUTF();
        
      new Mail(from, pass, to, subject, body); //envio mail con los datos del socket cliente
        
        
      dataClienteOut.writeByte(1);
      dataClienteOut.writeUTF("Envio de Mail Correcto");
      dataClienteOut.flush();
      dataClienteOut.close();
      dataClienteIn.close();
      client.close();
      }catch (IOException e) 
      {
         e.printStackTrace();
      }catch (Exception e)
      {
         e.printStackTrace();
         try {
        	
        	dataClienteOut.writeByte(2);
			dataClienteOut.writeUTF(e.getMessage());
			dataClienteOut.flush();
			dataClienteOut.close();
			dataClienteIn.close();
			client.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
      }
   	  

}

}
