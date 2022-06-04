package co.com.poli.projectcloud.projectcloud.mapper;

import co.com.poli.projectcloud.projectcloud.exceptions.ProjectCloudExceptions;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.persistence.entity.ProjectTasks;
import co.com.poli.projectcloud.projectcloud.service.BacklogService;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectTasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectTasksMapper implements IMapper<ProjectTasksDTO, ProjectTasks> {

    private final BacklogService backlogService;

    @Override
    public ProjectTasks map(ProjectTasksDTO in) {
        ProjectTasks projectTask = new ProjectTasks();
        Optional<Backlog> optionalBacklog;

        try{
            optionalBacklog  = backlogService.findById(in.getIdBacklog());
        }catch (InvalidDataAccessApiUsageException invalidDataAccessApiUsageException){
            throw new ProjectCloudExceptions("Debes ingresar el id del backlog", HttpStatus.BAD_REQUEST);
        }



        try {
            if (in.getName().isEmpty()) {
                throw new ProjectCloudExceptions("El nombre de la tarea no puede estar vacio", HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException nullPointerException) {
            throw new ProjectCloudExceptions("El nombre de la tarea debe existir, revisa DTO", HttpStatus.BAD_REQUEST);
        }

        try {
            if (in.getSummary().isEmpty()) {
                throw new ProjectCloudExceptions("El resumen de la tarea no puede estar vacio", HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException nullPointerException) {
            throw new ProjectCloudExceptions("El resumen de la tarea debe existir, revisa DTO", HttpStatus.BAD_REQUEST);
        }

        if (optionalBacklog.isPresent()) {
            Backlog backlog = optionalBacklog.get();
            projectTask.setName(in.getName());
            projectTask.setSummary(in.getSummary());
            projectTask.setAcceptanceCriteria(in.getAcceptanceCriteria());
            projectTask.setStatus(in.getStatus());
            projectTask.setPriority(in.getPriority());
            projectTask.setHours(in.getHours());
            projectTask.setStartDate(in.getStartDate());
            projectTask.setEndDate(in.getEndDate());
            projectTask.setProjectIdentifier(backlog.getProjectIdentifier());
            projectTask.setBacklog(backlog);
        }


        return projectTask;
    }
}
