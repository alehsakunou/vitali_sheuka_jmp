package mentoring.dao;

import mentoring.model.Project;
import mentoring.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vitali_Sheuka on 10/9/2016.
 */
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
