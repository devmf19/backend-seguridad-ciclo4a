package registraduria.misionTIC.seguridad.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.misionTIC.seguridad.Models.Role;
import registraduria.misionTIC.seguridad.Repositories.RoleRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RoleRepository rol_repository;

    @GetMapping("")
    public List<Role> index(){
        return this.rol_repository.findAll();
    }

    @GetMapping("{id}")
    public Role show(@PathVariable String id){
        return this.rol_repository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Role create(@RequestBody Role new_info){
        return this.rol_repository.save(new_info);
    }

    @PutMapping("{id}")
    public Role update(@PathVariable String id, @RequestBody Role info){
        Role rol = this.rol_repository.findById(id).orElse(null);
        rol.setDescripcion(info.getDescripcion());
        rol.setNombre(info.getNombre());
        return this.rol_repository.save(rol);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.rol_repository.deleteById(id);
    }
}
