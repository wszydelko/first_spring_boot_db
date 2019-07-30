package pl.mloza.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Project {
    @GeneratedValue
    @Id
    private int id;

    @Column
    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
