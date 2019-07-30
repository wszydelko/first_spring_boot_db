package pl.mloza.repozytory;

//import org.hibernate.mapping.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mloza.entity.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository <Task,Long> {
    /*
    * findByName- jako parametr podajemy "name" i spring sam zbuduje potrzebne implementacje tak żeby
    * w bazie wyszukał
    * */
    public List<Task> findByDone (Boolean done);//W artykuje brakowało <Task>

    /*
    * Typ wyszukania z adnotacją Query
    * */
    @Query("select t from Task t where t.description like %?1%")
    public List<Task> getByDescriptionLike(String search);

    /*
    * Adnotacja z parametrem -native
    * */
    //@Query(nativeQuery = true, "select t from Task t where t.description like %?1%")
    //public List<Task> getByDescriptionLikeNative(String search);
}