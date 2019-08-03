package pl.mloza.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @GeneratedValue
    @Id
    private int id;

    @Column
    private String name;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @OneToOne(mappedBy = "project")
    private ProjectDetails projectDetails;


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
