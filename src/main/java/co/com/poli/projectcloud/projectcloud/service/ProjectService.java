package co.com.poli.projectcloud.projectcloud.service;



import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface ProjectService {

    ResponseEntity<List<Project>> findAll();

    ResponseEntity<Project> createProject(ProjectDTO projectDTO);

    Optional<Project> findById(Long id);

}
