package se.jayway.twb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "model")
public class Model
{
    @Id
    @Column(name = "message")
    public String message;

    public Model(String message)
    {
        this.message = message;
    }

    public Model(){}
}
