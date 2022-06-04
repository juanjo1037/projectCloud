package co.com.poli.projectcloud.projectcloud.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProjectCloudExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public ProjectCloudExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
