package pl.mloza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mloza.entity.Project;
import pl.mloza.entity.ProjectDetails;
import pl.mloza.entity.Tag;
import pl.mloza.repozytory.ProjectDetailsRepository;
import pl.mloza.repozytory.ProjectRepository;
import pl.mloza.repozytory.TagRepository;
import pl.mloza.repozytory.TaskRepository;
import pl.mloza.entity.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public ProjectDetailsRepository projectDetailsRepository;

    @Autowired
    public TagRepository tagRepository;

    @RequestMapping("/db")
    @ResponseBody
    public String testMethod(){
        StringBuffer response = new StringBuffer();

        //Ddane do zapisania
        Task task = new Task()
                .withName("Task 1")
                .withDescription("Test task")
                .withBudget(123.55)
                .withDone(true);

        //zapisanie danych do bazy
        taskRepository.save(task);

        //Wypisanie aktualnego stanu bazy
        for(Task i: taskRepository.findAll()){
            response.append(i).append("<br>");
        }

        return response.toString();
    }

    @RequestMapping("/db2")
    @ResponseBody
    public String testMethod2(){
        //Zmienna do której będzie zapisana odpowiedź
        StringBuffer response = new StringBuffer();

        response.append("Tasks with done set to true<br>");
        for(Task i: taskRepository.findByDone(true)) {
            response.append(i).append("<br>");
        }
        //response.append(taskRepository.findByDone(true)).append("<br>");
        //response.append(taskRepository.findAll()).append("<br>");

        //Dodatkowe parametry wyszukiwania
        response.append("Tasks with \"DO\" in description");
        for(Task i: taskRepository.getByDescriptionLike("Do")){
            System.out.println(response.append(i).append("<br>"));

        }


        return response.toString();
    }
    //Kontroler prezentujący Projekty i powiązane z nim zadania
    @RequestMapping("/project-tasks")
    @ResponseBody
    public String projectsAndTasks(){
        StringBuffer response = new StringBuffer();

        for(Project project: projectRepository.findAll()){
            response.append(project).append("<br>");
            for(Task task: project.getTasks()){
                response.append(" - ").append(task).append("<br>");
            }
        }
        return response.toString();
    }

    //Dodanie zadanie do wybranego projektu
    //W tym przypadku ID=1
    @RequestMapping("/add-task")
    @ResponseBody
    public String addTask(){
        Project project = projectRepository.findOne(1);

        //Założenie nowego obiektu
        Task task = new Task()
                .withBudget(20.00)
                .withName("addtest")
                .withDescription("Add test")
                .withDone(false)
                .withProject(project);

        //Dodanie zadania do projektu
        project.getTasks().add(task);
        //Zapisanie danych do bazy danych
        taskRepository.save(task);
        //Zwracana jest nazwa kontrolera, który będzie uruchomiony nastepnie
        return projectsAndTasks();
        //return "<p>Test</p>";
    }

    //Kasowanie zadania z projkektu
    @RequestMapping("/delete-task")
    @ResponseBody
    public String deleteTask(){
        //Zadanie do usunięcia
        Task taskToDel = taskRepository.findOne(1L);

        if (taskToDel != null) { //warunek zabezpiecza przed ponownym usunięciem
            System.out.println("Blad");
            //Powiązany z zadaniem projekt
            Project project = taskToDel.getProject();

            //Usunięcie zadania z listy projektu
            project.getTasks().remove(taskToDel);

            //zapisanie zmodyfikowanie projektu do bazy
            taskRepository.delete(taskToDel);
        }

        return projectsAndTasks();
    }

    // Dodanie opisu do tabeli "ProjectDetails"
    @RequestMapping("/add-desc")
    @ResponseBody
    public String addDescription(){
        //Pobranie tabeli projektu
        Project project = projectRepository.findOne(1);


        //Dodanie obiektu Description
        ProjectDetails projectDetails = new ProjectDetails();
        projectDetails.setDescription("Test");
        projectDetails.setProject(project);

        projectDetailsRepository.save(projectDetails);

        return projectsAndTasks();
    }

    //Edit Project Description
    @RequestMapping("/edit-pro-desc")
    @ResponseBody
    public String editProjectDescription(){
        //Pobrannie projektu do edycji
        Project projectToEdit = projectRepository.findOne(1);
        //Pobranie Project details
        ProjectDetails projectDetails = projectToEdit.getProjectDetails();
        projectDetails.setDescription("Nowy opis");

        projectDetailsRepository.save(projectDetails);
        return projectsAndTasks();
    }

    //Obsługa TAG'ów
    @RequestMapping("/addTag")
    @ResponseBody
    public String addTag(){
        //Tag tag = tagRepository.findOne(1);

        /*Iterable<Tag> ii = new Iterable<Tag>() {
            @Override
            public Iterator<Tag> iterator() {
                return null;
            }
        };
        ii = tagRepository.findAll();

        for(Tag tagItem: tagRepository.findAll()){

        }*/

        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        tag1.setName("Java");
        tag2.setName(".Net");
        //Zapisanie zmian w tabeli Tag
        tagRepository.save(tag2);
        tagRepository.save(tag1);
        /*
        Project project1 = new Project();
        project1= projectRepository.findOne(1);
        project1.setTag(new ArrayList<Tag>());
        project1.getTag().add(tag1);

        projectRepository.save(project1);
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        */

        Project project1 = new Project();
        //project1.setName("Project 1");

        //Project project2 = new Project();
        //project2.setName("Project 2");
        //projectRepository.save(project1);
        //projectRepository.save(project2);

        //Wybranie projektu do modyfikacji
        project1 = projectRepository.findOne(1);
        //project1.setTag(new ArrayList<Tag>());
        //Dodanie tagów do projektu
        project1.getTag().add(tag1);
        project1.getTag().add(tag2);

        //project2.setTag(new ArrayList<Tag>());
        //project2.getTag().add(tag1);
        //project2.getTag().add(tag2);

        //Przypisanie projektu1 do Taga1
        tag1.setProjects(new ArrayList<Project>());
        tag1.getProjects().add(project1);
        //tag1.getProjects().add(project2);

        tag2.setProjects(new ArrayList<Project>());
        tag2.getProjects().add(project1);

        tagRepository.save(tag1);
        tagRepository.save(tag2);


        return projectsAndTasks();
    }

    //Pobranie projektu z Tag=Java
    @RequestMapping("/getTag")
    @ResponseBody
    public String getTag1(){
        Project project = new Project();
        project = projectRepository.findById(1);

        //return (projectRepository.findByName("Project 1")).getName();
        //return project.toString();
        return project.getName();
    }

    @RequestMapping("/")
    @ResponseBody
    public String mainPage(){
        return "Hello world :)";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo(){
        return "Hi";
    }

}