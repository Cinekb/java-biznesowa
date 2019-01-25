package wipb.jee.clientdemo.ejb;


import wipb.jee.clientdemo.model.Error;
import wipb.jee.clientdemo.model.Mail;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
public class MailDao {

    @PersistenceContext(unitName = "wipb_ee-std-client-demo-ejb_ejb_1.0PU")
    private EntityManager em;


    public List<Mail> findAll() {
        TypedQuery<Mail> q = em.createNamedQuery("Mail.findAll",Mail.class);
        return q.getResultList();
    }

    public void save(Mail mail) {
        em.persist(mail);
    }
}


