package se.iths.springloppis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.springloppis.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    // Om man vill ha något som inte är färdigimplementerat redan. Då kan man skapa den här.
    // Den visar först alternativ, alltså vad en tror att du vill skapa.
    // Den går in och kollar strukturen på entiteten och försöker lösa en metod till den.

}
