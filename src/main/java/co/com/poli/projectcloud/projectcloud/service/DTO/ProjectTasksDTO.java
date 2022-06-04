package co.com.poli.projectcloud.projectcloud.service.DTO;

import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.HoursRange;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.PriorityRange;
import co.com.poli.projectcloud.projectcloud.persistence.entity.enums.TaskStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProjectTasksDTO {

    private Long idBacklog;
    private String name;
    private String summary;
    private String acceptanceCriteria;
    private TaskStatus status;
    private int priority ;
    private double hours ;
    private LocalDate startDate;
    private LocalDate endDate;


}
