package com.company;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

class Mailer{
     String from="yashbattu@gmail.com";
    String password="DeepakKanoria246";
     Properties props=null;
     Session session=null;


    public Properties getProps()
    {
        if(props!=null)
            return props;
        else
        {
            props=new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            return props;

        }
    }
    public Session getSession()
    {
        if(session!=null)
            return session;
        else
        {

             session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from,password);
                        }
                    });
            return session;

        }
    }
    public  void send(String to,String sub,String msg){
        //Get properties object
        //get Session

        props=getProps();
        session=getSession();

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);

            message.setContent(msg, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("message sent successfully for"+to);
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}
public class SendMailSSL{
    public static void main(String[] args) {
        //from,password,to,subject,message





        Mailer mailer=new Mailer();
        //mailer.send("mohitkanoria1994@gmail.com","hello javatpoint","How r u?");

       // mailer.send("yshkanoria@hotmail.c","hello brodbill","How r u brodbill?");
        //change from, password and to
    }
}