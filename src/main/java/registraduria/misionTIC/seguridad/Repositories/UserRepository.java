package registraduria.misionTIC.seguridad.Repositories;

import org.springframework.data.mongodb.repository.Query;
import registraduria.misionTIC.seguridad.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'correo': ?0}")
    public User getUserByEmail(String email);
}
