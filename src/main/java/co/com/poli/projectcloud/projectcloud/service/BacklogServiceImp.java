package co.com.poli.projectcloud.projectcloud.service;


import co.com.poli.projectcloud.projectcloud.exceptions.ProjectCloudExceptions;
import co.com.poli.projectcloud.projectcloud.mapper.BacklogMapper;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.persistence.repository.BacklogRepository;
import co.com.poli.projectcloud.projectcloud.persistence.repository.ProjectRepository;
import co.com.poli.projectcloud.projectcloud.service.DTO.BacklogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BacklogServiceImp implements BacklogService{

    private final BacklogRepository backlogRepository;

    private final BacklogMapper mapper;

    private final ProjectService projectService;
    @Override
    public ResponseEntity<Backlog> createBacklog(BacklogDTO backlogDTO){
        Optional<Project> optionalProject = projectService.findById(backlogDTO.getProjectId());
        if(optionalProject.isPresent()){
            if(backlogRepository.existsByProjectIdentifier(optionalProject.get().getProjectIdentifier())){
                throw new ProjectCloudExceptions("Ya existe un backlog para este proyecto", HttpStatus.BAD_REQUEST);
            }
        }else throw new ProjectCloudExceptions("No existe un proyecto con este id", HttpStatus.BAD_REQUEST);

        Backlog backlog = mapper.map(backlogDTO);
        this.backlogRepository.save(backlog);
        return  new ResponseEntity<Backlog>(backlog, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Backlog>> findAll() {
        return new ResponseEntity<List<Backlog>>(backlogRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public Optional<Backlog> findById(Long id) {
        return this.backlogRepository.findById(id);
    }


}
