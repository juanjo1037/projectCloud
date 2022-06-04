package co.com.poli.projectcloud.projectcloud.persistence.repository;

import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.persistence.entity.ProjectTasks;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTasks, Long> {

//    @Query(value = "SELECT * FROM PROJECTTASK WHERE PROJECT_IDENTIFIER=:projectIdentifier AND STATUS<>:taskStatus", nativeQuery = true)
//    List<ProjectTasks> getAllByProjectIdentifier(String projectIdentifier, TaskStatus taskStatus);

    List<ProjectTasks> findAllByProjectIdentifierAndStatusIsNot(String projectIdentifier, TaskStatus taskStatus);

    List<ProjectTasks> findAllByProjectIdentifier(String projectIdentifier);

    List<ProjectTasks> findAllByProjectIdentifierAndStatus(String projectIdentifier, TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE PROJECTTASK SET STATUS=3 WHERE ID_PROJECT_TASK=:idTask AND PROJECT_IDENTIFIER=:projectIdentifier", nativeQuery = true)
    void patchProjectTaskByIdTaskAndProjectIdentifier(@Param("projectIdentifier") String projectIdentifier,@Param("idTask") Long idTask);

    boolean existsByProjectIdentifierAndId(String projectIdentifier, Long idTask);

}
