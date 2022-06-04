package co.com.poli.projectcloud.projectcloud.persistence.entity;

import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.HoursRange;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.IdentifierStatus;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.PriorityRange;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "projecttask")
public class ProjectTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ProjectTask", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;

    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Min(1)
    @Max(5)
    @Column(name = "priority", nullable = false)
    private int priority;

    @Min(1)
    @Max(8)
    @Column(name = "hours", nullable = false)
    private double hours;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "project_identifier", nullable = false, updatable = false)
    protected String projectIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id_backlog")
    @JsonBackReference
    private Backlog backlog;

}
