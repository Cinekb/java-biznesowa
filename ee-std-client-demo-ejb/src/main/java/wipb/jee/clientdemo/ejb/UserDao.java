/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.ejb;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import wipb.jee.clientdemo.model.Customer;


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
public class UserDao {
    @PersistenceContext(unitName = "wipb_ee-std-client-demo-ejb_ejb_1.0PU")
    private EntityManager em;

    public void save(Customer user) {
        em.persist(user);
    }

    public Customer update(Customer user) {
        return em.merge(user);
    }

    public void remove(Long id) {
        em.remove(em.getReference(Customer.class, id));
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> q = em.createNamedQuery("Customer.findAll",Customer.class);
        return q.getResultList();
    }


}
