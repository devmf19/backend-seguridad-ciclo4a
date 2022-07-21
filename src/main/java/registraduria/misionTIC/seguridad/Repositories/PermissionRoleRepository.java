package registraduria.misionTIC.seguridad.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import registraduria.misionTIC.seguridad.Models.PermissionRole;


public interface PermissionRoleRepository extends MongoRepository<PermissionRole, String> {
}
