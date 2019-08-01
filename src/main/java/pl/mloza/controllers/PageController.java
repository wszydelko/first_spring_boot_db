package pl.mloza.controllers;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mloza.entity.Project;
import pl.mloza.repozytory.ProjectRepository;
import pl.mloza.repozytory.TaskRepository;
import pl.mloza.entity.Task;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public ProjectRepository projectRepository;

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

    @RequestMapping("/project-tasks")
    @ResponseBody
    public String projectsAndTasks(){
        StringBuffer response = new StringBuffer();

        for(Project project: projectRepository.findAll()){
            response.append(project).append("<br>");
            for(Task task: project.getTasks()){
                response.append(task).append("<br>");
            }
        }
        return response.toString();
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
