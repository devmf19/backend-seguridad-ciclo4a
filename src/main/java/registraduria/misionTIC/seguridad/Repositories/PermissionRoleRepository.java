package registraduria.misionTIC.seguridad.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.misionTIC.seguridad.Models.PermissionRole;


public interface PermissionRoleRepository extends MongoRepository<PermissionRole, String> {
    @Query("{'role.$id': ObjectId(?0), 'permission.$id': ObjectId(?1)}")
    PermissionRole getPermissionRole(String id_role, String id_permission);
}
