package co.com.poli.projectcloud.projectcloud.service;


import co.com.poli.projectcloud.projectcloud.persistence.entity.ProjectTasks;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.TaskStatus;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectTasksDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ProjectTasksService {

    ResponseEntity<ProjectTasks> createProjectTaskDTO(ProjectTasksDTO projectTaksDTO);

    ResponseEntity<List<ProjectTasks>> findAll();

    ResponseEntity<List<ProjectTasks>>  getAllByProjectIdentifier(String projectIdentifier);

    List<ProjectTasks> getAllByProjectIdentifierAndStatusIsNotDeleted(String projectIdentifier);

    ResponseEntity<String>  getProjectHoursByProjectIdentifierAndStatus(String projectIdentifier, TaskStatus taskStatus);

    ResponseEntity<String> getProjectTotalHours(String projectIdentifier);

    ResponseEntity<String> patchProjectTaskByIdTaskAndProjectIdentifier(String projectIdentifier, Long idTask);




}
