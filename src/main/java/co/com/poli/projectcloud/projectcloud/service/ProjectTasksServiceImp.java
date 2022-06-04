package co.com.poli.projectcloud.projectcloud.service;

import co.com.poli.projectcloud.projectcloud.exceptions.ProjectCloudExceptions;
import co.com.poli.projectcloud.projectcloud.mapper.ProjectTasksMapper;
import co.com.poli.projectcloud.projectcloud.persistence.entity.ProjectTasks;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.TaskStatus;
import co.com.poli.projectcloud.projectcloud.persistence.repository.ProjectTaskRepository;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectTasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTasksServiceImp implements ProjectTasksService {

    private final ProjectTaskRepository projectTaskRepository;

    private final ProjectTasksMapper mapper;

    @Override
    public ResponseEntity<ProjectTasks> createProjectTaskDTO(ProjectTasksDTO projectTasksDTO) {
        ProjectTasks projectTask = mapper.map(projectTasksDTO);

        try {
            this.projectTaskRepository.save(projectTask);
            return new ResponseEntity<ProjectTasks>(projectTask, HttpStatus.CREATED);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ProjectCloudExceptions("El rango de la prioridad debe ser entre 1 y 5. " +
                    "\n El rango de las horas debe ser entre 1 y 8", HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity<List<ProjectTasks>> findAll() {

        return new ResponseEntity<List<ProjectTasks>>(projectTaskRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<ProjectTasks>> getAllByProjectIdentifier(String projectIdentifier) {
        return new ResponseEntity<List<ProjectTasks>>(projectTaskRepository.findAllByProjectIdentifier(projectIdentifier), HttpStatus.ACCEPTED);
    }

    @Override
    public List<ProjectTasks> getAllByProjectIdentifierAndStatusIsNotDeleted(String projectIdentifier) {
        return projectTaskRepository.findAllByProjectIdentifierAndStatusIsNot(projectIdentifier, TaskStatus.deleted);
    }

    @Override
    public ResponseEntity<String> getProjectHoursByProjectIdentifierAndStatus(String projectIdentifier, TaskStatus taskStatus) {
        double totalHours = 0;
        List<ProjectTasks> projectTasks = projectTaskRepository.findAllByProjectIdentifierAndStatus(projectIdentifier, taskStatus);
        for (ProjectTasks projectTask : projectTasks) {
            totalHours += projectTask.getHours();
        }
        return new ResponseEntity<String>("Total de horas del proyecto " + projectIdentifier + "con el Status " + taskStatus + " es: " + totalHours, HttpStatus.ACCEPTED);

    }


    @Override
    public ResponseEntity<String> getProjectTotalHours(String projectIdentifier) {
        double totalHours = 0;
        List<ProjectTasks> projectTasks = this.getAllByProjectIdentifierAndStatusIsNotDeleted(projectIdentifier);
        for (ProjectTasks projectTask : projectTasks) {
            totalHours += projectTask.getHours();
        }

        return new ResponseEntity<String>("Total de horas del proyecto " + projectIdentifier + ": " + totalHours, HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> patchProjectTaskByIdTaskAndProjectIdentifier(String projectIdentifier, Long idTask) {
        if (!projectTaskRepository.existsByProjectIdentifierAndId(projectIdentifier, idTask)) {
            throw new ProjectCloudExceptions("No existe una tarea con esos datos", HttpStatus.BAD_REQUEST);
        }
        projectTaskRepository.patchProjectTaskByIdTaskAndProjectIdentifier(projectIdentifier, idTask);
        return new ResponseEntity<String>("Tarea actualizada", HttpStatus.OK);


    }


}
