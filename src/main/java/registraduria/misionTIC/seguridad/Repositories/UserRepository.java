package registraduria.misionTIC.seguridad.Repositories;

import registraduria.misionTIC.seguridad.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
