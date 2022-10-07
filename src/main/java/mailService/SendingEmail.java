package mailService;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail {
    private static final  Dotenv dotenv = Dotenv.load();
    private static final String MAIL_PASS = dotenv.get("MAIL_PASS");
    private static final String USER_EMAIL = dotenv.get("USER_EMAIL");
    private static final String FROM = dotenv.get("USER_EMAIL");
    private static final boolean SMTP_DEBUG = false;

    public static void send(String toAddress, String subject, String text) {
        // Recipient's email ID needs to be mentioned.
        String to = toAddress; // email

        // Sender's email ID needs to be mentioned
        String from = FROM;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_EMAIL, MAIL_PASS);
            }
        });

        // Used to debug SMTP issues
        session.setDebug(SMTP_DEBUG);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}