package servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NotificadorMail
{
   private ServerSocket server;

   public static void main(String[] args)
   {
      NotificadorMail notificador = new NotificadorMail(); //Creo e inicio el servicio
      notificador.IniciarServicio();
   }
   public void IniciarServicio(){
      System.out.println("Iniciando Servicio...");
      try
      {
        server = new ServerSocket(4444);
        System.out.println("Escuchando en puerto 4444");
      } catch (IOException e) {
        System.out.println("No se pudo iniciar el servicio en el puerto 4444");
        System.exit(-1);
      }
      while(true){
        try
        {
           //Retornar la conexion con el cliente crea un nuevo socket para cada aceptar varios clientes en simultaneo
           Worker worker = new Worker(server.accept());
           Thread nuevaConexion = new Thread(worker);
           nuevaConexion.start();
        } catch (IOException e) {
          System.out.println("No se pudo aceptar el cliente en el puerto: 4444");
          System.exit(-1);
        }
      }
    }
   public void killServicio() throws IOException
   {
      server.close();
   }

}
