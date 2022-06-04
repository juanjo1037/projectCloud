package co.com.poli.projectcloud.projectcloud.service;

import co.com.poli.projectcloud.projectcloud.exceptions.ProjectCloudExceptions;
import co.com.poli.projectcloud.projectcloud.mapper.ProjectMapper;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.persistence.repository.ProjectRepository;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;

    @Override
    public ResponseEntity<List<Project>> findAll() {
        return  new ResponseEntity<List<Project>>(projectRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Project> createProject(ProjectDTO projectDTO) {
        if(projectRepository.existsByProjectName(projectDTO.getProjectName())){
            throw new ProjectCloudExceptions("Ya existe un proyecto con ese nombre", HttpStatus.BAD_REQUEST);
        }
        Project project = mapper.map(projectDTO);
        this.projectRepository.save(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return this.projectRepository.findById(id);
    }
}
