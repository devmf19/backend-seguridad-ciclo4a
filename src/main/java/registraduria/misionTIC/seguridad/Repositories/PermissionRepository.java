package registraduria.misionTIC.seguridad.Repositories;

import org.springframework.data.mongodb.repository.Query;
import registraduria.misionTIC.seguridad.Models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    @Query("{'url': ?0, 'metodo': ?1}")
    Permission getPermission(String url, String method);
}
