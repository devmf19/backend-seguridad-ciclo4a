package registraduria.misionTIC.seguridad.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.misionTIC.seguridad.Models.Permission;
import registraduria.misionTIC.seguridad.Models.PermissionRole;
import registraduria.misionTIC.seguridad.Models.Role;
import registraduria.misionTIC.seguridad.Repositories.PermissionRepository;
import registraduria.misionTIC.seguridad.Repositories.PermissionRoleRepository;
import registraduria.misionTIC.seguridad.Repositories.RoleRepository;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("permission-role")
public class PermissionRoleController {

    @Autowired
    private RoleRepository role_repository;
    @Autowired
    private PermissionRepository permission_repository;
    @Autowired
    private PermissionRoleRepository permission_role_repository;

    @GetMapping("")
    public List<PermissionRole> index(){
        return this.permission_role_repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("role/{id_role}/permission/{id_permission}")
    public PermissionRole create(@PathVariable String id_role, @PathVariable String id_permission){
        Role role = this.role_repository.findById(id_role).orElse(null);
        Permission permission = this.permission_repository.findById(id_permission).orElse(null);
        PermissionRole permission_role = new PermissionRole();
        if(permission == null ||  role == null) {
            return null;
        }else{
            permission_role.setRole(role);
            permission_role.setPermission(permission);
            return this.permission_role_repository.save(permission_role);
        }
    }


    @GetMapping("{id}")
    public PermissionRole show(@PathVariable String id){
        return this.permission_role_repository.findById(id).orElse(null);
    }


    @PutMapping("{id_permission_role}/role/{id_role}/permission/{id_permission}")
    public PermissionRole update(@PathVariable String id_permission_role, @PathVariable String id_role, @PathVariable String id_permission){
        Role role = this.role_repository.findById(id_role).orElse(null);
        Permission permission = this.permission_repository.findById(id_permission).orElse(null);
        PermissionRole permission_role = this.permission_role_repository.findById(id_permission_role).orElse(null);
        if(role == null || permission == null || permission_role == null){
            return null;
        }else{
            permission_role.setPermission(permission);
            permission_role.setRole(role);
            return this.permission_role_repository.save(permission_role);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.permission_role_repository.deleteById(id);
    }


}
