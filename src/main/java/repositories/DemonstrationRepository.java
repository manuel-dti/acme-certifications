
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.DemonstrationEntity;

@Repository
public interface DemonstrationRepository extends JpaRepository<DemonstrationEntity, Integer> {

}
