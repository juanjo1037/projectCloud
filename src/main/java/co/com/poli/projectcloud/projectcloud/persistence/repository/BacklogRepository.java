package co.com.poli.projectcloud.projectcloud.persistence.repository;


import co.com.poli.projectcloud.projectcloud.persistence.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    boolean existsByProjectIdentifier(String projectIdentifier);

}
