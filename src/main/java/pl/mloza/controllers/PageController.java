package pl.mloza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mloza.entity.Project;
import pl.mloza.entity.ProjectDetails;
import pl.mloza.repozytory.ProjectDetailsRepository;
import pl.mloza.repozytory.ProjectRepository;
import pl.mloza.repozytory.TaskRepository;
import pl.mloza.entity.Task;

@Controller
public class PageController {

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public ProjectDetailsRepository projectDetailsRepository;

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