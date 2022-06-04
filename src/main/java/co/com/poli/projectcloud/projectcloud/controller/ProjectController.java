package co.com.poli.projectcloud.projectcloud.controller;

import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectDTO;
import co.com.poli.projectcloud.projectcloud.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createTask(@RequestBody ProjectDTO projectDTO) {

        return this.projectService.createProject(projectDTO);
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {

        return this.projectService.findAll();
    }


}
