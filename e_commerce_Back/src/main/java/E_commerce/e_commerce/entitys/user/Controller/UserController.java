package E_commerce.e_commerce.entitys.user.Controller;

import E_commerce.e_commerce.entitys.user.Service.UserService;
import E_commerce.e_commerce.entitys.user.userDTO.UserLoginDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserResponseDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserRegisterDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permite requisições do Live Server
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Criar usuario
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRegisterDTO dto) {

        return ResponseEntity.ok(userService.createUser(dto));
    }

    //ACcar usuario pelo id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        return userService.findById(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    //Listar usuarios
    @GetMapping
    public List<UserResponseDTO> listUsers(){
        return userService.listUsers();
    }

    //Atualizar usuarios
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO dto) {

        return ResponseEntity.ok(userService.updateUser(id,dto));
    }

    //Deletar o usuario pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

    //Login do usuario
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid UserLoginDTO dto){
        UserResponseDTO user = userService.login(dto);

        return ResponseEntity.ok(user);
    }
}
