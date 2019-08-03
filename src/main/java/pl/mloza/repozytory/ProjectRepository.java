package pl.mloza.repozytory;

import org.springframework.data.repository.CrudRepository;
import pl.mloza.entity.Project;

public interface ProjectRepository extends CrudRepository<Project,Integer> { }
