package E_commerce.e_commerce.entitys.user.Service;

import E_commerce.e_commerce.entitys.user.Repository.UserRepository;
import E_commerce.e_commerce.entitys.user.User;
import E_commerce.e_commerce.entitys.user.userDTO.UserRegisterDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    //Criar usuario
    //Se o email n for cadastrado
    //Se n existir um username igual
    public User createUser(UserRegisterDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Esse e-mail já foi cadastrado");
        }

        if(repository.existsByUsername(dto.getUsername())){
            throw new IllegalArgumentException("Esse nome de usuário já está em uso");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encryptedPassword);

        return repository.save(user);
    }

    //Listar todos os usuarios
    public List<User> listUsers(){
        return repository.findAll();
    }

    //Listar o usuario pelo id
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    //Atualizar o usuario se ele existir
    public User updateUser(Long id, UserRegisterDTO dto){

        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());

        if(dto.getPassword() != null && !dto.getPassword().isBlank()){
            String encryptedPassword = passwordEncoder.encode(dto.getPassword());
            user.setPassword(encryptedPassword);
        }

        return repository.save(user);
    }

    public void deleteUserById(Long id){

       if(!repository.existsById(id)){
           throw new IllegalArgumentException("Esse usuario não existe");
       }

        repository.deleteById(id);
    }

    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

}
