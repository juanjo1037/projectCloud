package co.com.poli.projectcloud.projectcloud.service.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProjectDTO {

    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;



}
