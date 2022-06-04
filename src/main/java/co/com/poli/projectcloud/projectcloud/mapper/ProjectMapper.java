package co.com.poli.projectcloud.projectcloud.mapper;

import co.com.poli.projectcloud.projectcloud.exceptions.ProjectCloudExceptions;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.service.DTO.BacklogDTO;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements IMapper<ProjectDTO, Project> {


    @Override
    public Project map(ProjectDTO in) {
        Project project = new Project();

        try {
            if (in.getProjectName().isEmpty()) {
                throw new ProjectCloudExceptions("El nombre del proyecto no puede estar vacio", HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException nullPointerException) {
            throw new ProjectCloudExceptions("El nombre del proyecto debe existir, revisa DTO", HttpStatus.BAD_REQUEST);
        }

        try {
            if (in.getDescription().isEmpty()) {
                throw new ProjectCloudExceptions("La descripcion del proyecto no puede estar vacio", HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException nullPointerException) {

            throw new ProjectCloudExceptions("La descripción del proyecto debe existir, revisa DTO", HttpStatus.BAD_REQUEST);
        }


        project.setProjectName(in.getProjectName());
        project.setDescription(in.getDescription());
        project.setStartDate(in.getStartDate());
        project.setEndDate(in.getEndDate());
        project.setProjectIdentifier(RandomStringUtils.randomAlphanumeric(5, 7));
        return project;
    }
}
