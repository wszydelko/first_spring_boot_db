package pl.mloza.repozytory;

import org.springframework.data.repository.CrudRepository;
import pl.mloza.entity.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Integer> {
    public Project findById(int id);
}
