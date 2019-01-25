package wipb.jee.clientdemo.model;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Error.findAll", query = "select p from Error p")
public class Error implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String tekst;
    private Long post_id;

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
    //    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
//    Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTekst() {
        return tekst;
    }
//
//    public Post getPost() {
//        return post;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    }


