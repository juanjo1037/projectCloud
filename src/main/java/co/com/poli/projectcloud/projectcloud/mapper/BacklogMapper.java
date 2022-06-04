package co.com.poli.projectcloud.projectcloud.mapper;

import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.service.DTO.BacklogDTO;
import co.com.poli.projectcloud.projectcloud.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BacklogMapper implements IMapper<BacklogDTO, Backlog> {

    private final ProjectService projectService;

    @Override
    public Backlog map(BacklogDTO in) {
        Backlog backlog = new Backlog();
        Optional<Project> optionalProject = projectService.findById(in.getProjectId());
        if (optionalProject.isPresent()){
            Project project = optionalProject.get();
            backlog.setProjectIdentifier(project.getProjectIdentifier());
            project.setBacklog(backlog);
        }
        return backlog;
    }

}
