package E_commerce.e_commerce.entitys.user.Controller;

import E_commerce.e_commerce.entitys.user.Service.UserService;
import E_commerce.e_commerce.entitys.user.User;
import E_commerce.e_commerce.entitys.user.userDTO.UserRegisterDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserRegisterDTO dto){
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        return userService.findById(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserResponseDTO> listUsers(){
        return userService.listUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRegisterDTO dto) {

        return ResponseEntity.ok(userService.updateUser(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }
}
