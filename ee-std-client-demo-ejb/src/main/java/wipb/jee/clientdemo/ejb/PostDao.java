/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.ejb;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import javafx.geometry.Pos;
 import wipb.jee.clientdemo.model.Post;


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
public class PostDao {
    @PersistenceContext(unitName = "wipb_ee-std-client-demo-ejb_ejb_1.0PU")
    private EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post update(Post post) {
        return em.merge(post);
    }

    public void remove(Long id) {
        em.remove(em.getReference(Post.class, id));
    }

    public List<Post> findAll() {
        TypedQuery<Post> q = em.createNamedQuery("Post.findAll",Post.class);
        return q.getResultList();
    }
public  Post getpost(Long idd){
        TypedQuery<Post> q = em.createNamedQuery("Post.getpost",Post.class);
        q.setParameter("idd",idd);
        return q.getSingleResult();
}
}
