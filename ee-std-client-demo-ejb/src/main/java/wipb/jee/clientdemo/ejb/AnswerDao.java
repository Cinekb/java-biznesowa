package wipb.jee.clientdemo.ejb;

import wipb.jee.clientdemo.model.Answer;
import wipb.jee.clientdemo.model.Post;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
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
public class AnswerDao {
    @PersistenceContext(unitName = "wipb_ee-std-client-demo-ejb_ejb_1.0PU")
    private EntityManager em;

    public void save(Answer answer) {
        em.persist(answer);
    }

    public Answer update(Answer answer) {
        return em.merge(answer);
    }

    public void remove(Long id) {
        em.remove(em.getReference(Answer.class, id));
    }

    public List<Answer> findAll() {
        TypedQuery<Answer> q = em.createNamedQuery("Answer.findAll",Answer.class);
        return q.getResultList();
    }
    public  List<Answer> getAns(Long idd){
        TypedQuery<Answer> q = em.createNamedQuery("Answer.getAns",Answer.class);
        q.setParameter("idd",idd);
        return q.getResultList();
    }

}

