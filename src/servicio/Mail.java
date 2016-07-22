package servicio;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail
{
   public Mail(String from,String pass,String to,String subject,String body) throws Exception {
       sendFromGMail(from, pass, to, subject, body);
   }

   private void sendFromGMail(String from, String pass, String to, String subject, String body) throws Exception {
       Properties props = System.getProperties();
       String host = "smtp.gmail.com";
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.user", from);
       props.put("mail.smtp.password", pass);
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");

       Session session = Session.getDefaultInstance(props);
       MimeMessage message = new MimeMessage(session);

       try {
           message.setFrom(new InternetAddress(from));
           message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject(subject);
           message.setText(body);
           Transport transport = session.getTransport("smtp");
           transport.connect(host, from, pass);
           transport.sendMessage(message, message.getAllRecipients());
           transport.close();
       }
       catch (AddressException ae) {
          throw ae;
       }
       catch (MessagingException me) {
          throw me;
       }
   }
}
