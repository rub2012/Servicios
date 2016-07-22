package servicio;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EnvioMailTest extends TestCase
{

//private NotificadorMail notificador;
   //mail:pp2mailsender
   //pass:mailmail
private int port;
private String host;
private Socket socket;
private DataOutputStream mensaje;


public EnvioMailTest( String testName )
{
    super( testName );
}

public static Test suite()
{
    return new TestSuite( EnvioMailTest.class );
}

public void  setUp()
{
//   notificador = new NotificadorMail();
   host = "127.0.0.1";
   port = 4444;
   //EnvioMail();
}


public void EnvioMail()
{
   try
   {
      socket = new Socket(host,port);
      mensaje = new DataOutputStream(socket.getOutputStream());
      
      mensaje.writeUTF("pp2mailsender");
      mensaje.flush();
      
      mensaje.writeUTF("mailmail");
      mensaje.flush();
      
      mensaje.writeUTF("missgeek@mailinator.com");
      mensaje.flush();
      
      mensaje.writeUTF("Subject Test");
      mensaje.flush();
      
      mensaje.writeUTF("Body Test Envio de mail 2 de prueba para PP2");
      mensaje.flush();
      
      mensaje.close();
      socket.close();
      
   } catch (UnknownHostException e)
   {
      // TODO Auto-generated catch block
      e.printStackTrace();
   } catch (IOException e)
   {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
}
public void testWorker()
{
//   try
//   {
//      //OutputStream stdin = process.getOutputStream(); // <- Eh?
//      //InputStream stdout = process.getInputStream();
//
//      //BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
//      //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
//      InputStream io = socket.getInputStream();
//      //socket.
//   } catch (IOException e)
//   {
//   }
}
}