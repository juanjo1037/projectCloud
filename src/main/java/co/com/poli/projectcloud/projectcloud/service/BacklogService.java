package co.com.poli.projectcloud.projectcloud.service;

import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.service.DTO.BacklogDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BacklogService {
    ResponseEntity<Backlog> createBacklog(BacklogDTO backlogDTO);

   ResponseEntity <List<Backlog>> findAll();

    Optional<Backlog> findById(Long id);



}
