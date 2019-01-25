package wipb.jee.clientdemo.ejb;

import wipb.jee.clientdemo.model.Error;

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
public class ErrorDao {
    @PersistenceContext(unitName = "wipb_ee-std-client-demo-ejb_ejb_1.0PU")
    private EntityManager em;


    public Error update(Error error) {
        return em.merge(error);
    }

    public void remove(Long id) {
        em.remove(em.getReference(Error.class, id));
    }

    public List<Error> findAll() {
        TypedQuery<Error> q = em.createNamedQuery("Error.findAll",Error.class);
        return q.getResultList();
    }

    public void save(Error errorp) {
        em.persist(errorp);
    }
}
