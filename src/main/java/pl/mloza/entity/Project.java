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

    //Powiązanie z tagami
    //definicja contrain
    @ManyToMany(mappedBy = "projects")
    private List<Tag> tag;


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

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + projectDetails +
                '}';
    }
}
