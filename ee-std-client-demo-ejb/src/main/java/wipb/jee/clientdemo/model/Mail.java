package wipb.jee.clientdemo.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Mail.findAll", query = "select p from Mail p")
public class Mail  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
