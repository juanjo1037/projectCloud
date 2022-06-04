package co.com.poli.projectcloud.projectcloud.controller;

import co.com.poli.projectcloud.projectcloud.persistence.entity.ProjectTasks;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.TaskStatus;
import co.com.poli.projectcloud.projectcloud.service.DTO.ProjectTasksDTO;
import co.com.poli.projectcloud.projectcloud.service.ProjectTasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class ProjectTasksController {


    private final ProjectTasksService projectTasksService;

    @PostMapping
    public ResponseEntity<ProjectTasks> createProjectTasks(@RequestBody ProjectTasksDTO projectTasksDTO) {

        return this.projectTasksService.createProjectTaskDTO(projectTasksDTO);

    }

    @GetMapping
    public ResponseEntity<List<ProjectTasks>> findAll() {

        return this.projectTasksService.findAll();
    }

    @GetMapping("/project/{projectIdentifier}")
    public ResponseEntity<List<ProjectTasks>> getAllTasksByProject(@PathVariable("projectIdentifier") String projectIdentifier) {

        return projectTasksService.getAllByProjectIdentifier(projectIdentifier);
    }

    @GetMapping("/project/hours/{projectIdentifier}")
    public ResponseEntity<String> getProjectTotalHours(@PathVariable("projectIdentifier") String projectIdentifier) {
        return projectTasksService.getProjectTotalHours(projectIdentifier);
    }
    @GetMapping("/project/hours/{projectIdentifier}/{status}")
    public ResponseEntity<String> getAllHoursByProjectIdentifierWithStatus(@PathVariable("projectIdentifier") String projectIdentifier, @PathVariable("status") TaskStatus status) {
        return projectTasksService.getProjectHoursByProjectIdentifierAndStatus(projectIdentifier, status);
    }

    @PatchMapping("/{idTask}/{projectIdentifier}")
    public ResponseEntity<String> patchTask(@PathVariable("idTask") Long idTask , @PathVariable("projectIdentifier") String projectIdentifier){

        return projectTasksService.patchProjectTaskByIdTaskAndProjectIdentifier(projectIdentifier,idTask);

    }



}
