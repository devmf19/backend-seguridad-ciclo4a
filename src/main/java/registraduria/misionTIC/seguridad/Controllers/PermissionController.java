package registraduria.misionTIC.seguridad.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.misionTIC.seguridad.Models.Permission;
import registraduria.misionTIC.seguridad.Repositories.PermissionRepository;

import java.util.List;

@CrossOrigin
@RequestMapping("permissions")
@RestController
public class PermissionController {

    @Autowired
    private PermissionRepository permission_repository;

    @GetMapping("")
    public List<Permission> index(){
        return this.permission_repository.findAll();
    }

    @GetMapping("{id}")
    public Permission show(@PathVariable String id){
        return this.permission_repository.findById(id).orElse(null);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permission create(@RequestBody Permission new_permission){
        return this.permission_repository.save(new_permission);
    }

    @PutMapping("{id}")
    public Permission update(@PathVariable String id, @RequestBody Permission info){
        Permission permission = this.permission_repository.findById(id).orElse(null);
        permission.setMetodo(info.getMetodo());
        permission.setUrl(info.getUrl());
        return this.permission_repository.save(permission);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.permission_repository.deleteById(id);
    }
}
