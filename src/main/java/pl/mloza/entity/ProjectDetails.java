package pl.mloza.entity;

import javax.persistence.*;

@Entity
public class ProjectDetails {
    @GeneratedValue
    @Id
    private int id;

    @Column
    private String description;

    //Powiązanie do tabeli Project
    @OneToOne
    Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectDetails{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
