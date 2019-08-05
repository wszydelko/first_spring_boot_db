package pl.mloza.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {

    @GeneratedValue
    @Id
    private int id;

    @Column
    private String name;

    //Powiązanie z tabelą project
    //Definicja klucza obcego
    @ManyToMany
    private List<Project> projects;

    //Geters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}