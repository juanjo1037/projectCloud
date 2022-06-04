package co.com.poli.projectcloud.projectcloud.controller;

import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import co.com.poli.projectcloud.projectcloud.service.BacklogServiceImp;
import co.com.poli.projectcloud.projectcloud.service.DTO.BacklogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/backlogs")
public class BacklogController {

    @Autowired
    private BacklogServiceImp backlogServiceImp;

    @GetMapping
    public ResponseEntity<List<Backlog>> findAll() {

        return this.backlogServiceImp.findAll();
    }

    @PostMapping
    public ResponseEntity<Backlog> createBacklog(@RequestBody BacklogDTO backlogDTO) {

        return this.backlogServiceImp.createBacklog(backlogDTO);

    }


}
