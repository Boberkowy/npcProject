package info.owerczuk.npcProject.DAO;

import info.owerczuk.npcProject.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 */

public interface UserRepository extends CrudRepository<User,Long>{

    User findByPassword(String password);
    User findByUsername(String username);
}
