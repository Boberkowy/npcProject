package info.owerczuk.npcProject.DAO;

import info.owerczuk.npcProject.Domain.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 */
@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Contacts c SET c.firstname = ?2, c.lastname = ?3, c.email=?4, c.category=?5, c.phoneNumber=?6, c.dateOfBirth=?7  WHERE c.id = ?1")

    List<Contacts> getAllByIdIsNotNull();


}
