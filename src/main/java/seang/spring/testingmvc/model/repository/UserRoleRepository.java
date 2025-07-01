package seang.spring.testingmvc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seang.spring.testingmvc.model.entity.UserRoles;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
}
