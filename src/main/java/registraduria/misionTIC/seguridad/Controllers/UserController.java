package registraduria.misionTIC.seguridad.Controllers;

import registraduria.misionTIC.seguridad.Models.Role;
import registraduria.misionTIC.seguridad.Models.User;
import registraduria.misionTIC.seguridad.Repositories.RoleRepository;
import registraduria.misionTIC.seguridad.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public String convertSHA256(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b :
                hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @GetMapping("")
    public List<User> index(){
        return this.userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User newUser){
        String password = newUser.getContrasena();
        newUser.setContrasena(convertSHA256(password));
        return this.userRepository.save(newUser);
    }

    @GetMapping("/{id}")
    public User show(@PathVariable String id){
        User actualUser = this.userRepository.findById(id).orElse(null);
        return actualUser;
    }

    @PutMapping({"id"})
    public User update(@PathVariable String id, @RequestBody User infoUser){
        User actualUser = this.userRepository.findById(id).orElse(null);
        if(actualUser != null){
            actualUser.setSeudonimo(infoUser.getSeudonimo());
            actualUser.setCorreo(infoUser.getCorreo());
            actualUser.setContrasena(infoUser.getContrasena());

            return this.userRepository.save(actualUser);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        User actualUser = this.userRepository.findById(id).orElse(null);
        if(actualUser != null){
            this.userRepository.delete(actualUser);
        }
    }

    @PutMapping("{id_user}/role/{id_role}")
    public User addRoleToUser(@PathVariable String id_user, @PathVariable String id_role){
        User user=this.userRepository.findById(id_user).orElse(null);
        Role rol= this.roleRepository.findById(id_role).orElse(null);
        user.setRole(rol);
        return this.userRepository.save(user);
    }
}
