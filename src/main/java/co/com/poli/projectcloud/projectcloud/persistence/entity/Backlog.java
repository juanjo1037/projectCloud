package co.com.poli.projectcloud.projectcloud.persistence.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity

@Table(name = "backlog")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_backlog", nullable = false)
    private Long id;

    @Column(name = "project_identifier", nullable = false)
    private String projectIdentifier;

    @OneToOne(mappedBy = "backlog")
    @JsonBackReference
    private Project project;

    @OneToMany(mappedBy = "backlog")
    @JsonManagedReference
    private List<ProjectTasks> projectTask;

    public Backlog() {

    }
}
