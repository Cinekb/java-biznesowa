package wipb.jee.clientdemo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Answer.findAll", query = "select p from Answer p"),
                @NamedQuery(name = "Answer.getAns", query = "select a from  Answer  a ,Post p where p.id=:idd and a.post=p")
        })
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String tekst;
    @ManyToOne
    private Post post;

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

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
        post.getAnswers().add(this);
    }
}

