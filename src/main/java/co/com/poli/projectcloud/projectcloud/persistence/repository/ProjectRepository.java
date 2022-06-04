package co.com.poli.projectcloud.projectcloud.persistence.repository;

import co.com.poli.projectcloud.projectcloud.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByProjectName(String name);

}
