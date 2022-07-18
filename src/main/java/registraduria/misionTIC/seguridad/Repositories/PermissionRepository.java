package registraduria.misionTIC.seguridad.Repositories;

import registraduria.misionTIC.seguridad.Models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
