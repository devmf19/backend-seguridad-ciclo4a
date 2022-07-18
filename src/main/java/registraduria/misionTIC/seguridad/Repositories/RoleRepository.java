package registraduria.misionTIC.seguridad.Repositories;

import registraduria.misionTIC.seguridad.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
