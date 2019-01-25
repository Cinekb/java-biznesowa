package wipb.jee.clientdemo.ejb;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


@DataSourceDefinition(
        name="jdbc/mypool",
        className="org.apache.derby.jdbc.ClientDataSource",
        minPoolSize = 1,
        initialPoolSize = 1,
        portNumber = 1527,
        serverName = "localhost",
        user = "app",
        password = "app",
        databaseName = "testdb1",
        properties = {"connectionAttributes=;create=true"}
)

@Stateless
public class SendMail {
    private final static Logger log = Logger.getLogger(SendMail.class.getName());
    @Asynchronous
    public void sendMail(String to) {
        log.info("Sendnding mail to:"+to);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props);
        MimeMessage m = new MimeMessage(session);
        try {
            InternetAddress[] address = {new InternetAddress(to)};
            m.setFrom(new InternetAddress("forumtest49@gmail.com"));
            m.setRecipients(Message.RecipientType.TO, address);
            m.setSubject("Dodanie nowego tematu na forum");
            m.setSentDate(new Date());
            m.setText("Dodanie nowego tematu na forum");
            Transport.send(m,"forumtest49@gmail.com","qwerty1332");
        } catch (MessagingException e) {
            log.log(Level.SEVERE, "Error while sending mail", e);
        }
    }
}