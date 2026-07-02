package E_commerce.e_commerce.entitys.user.Service;

import E_commerce.e_commerce.entitys.user.Repository.UserRepository;
import E_commerce.e_commerce.entitys.user.User;
import E_commerce.e_commerce.entitys.user.userDTO.UserRegisterDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserResponseDTO;
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

        if (repository.existsByUsername(dto.getUsername())) {
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
    public List<UserResponseDTO> listUsers() {
        return repository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getTelephone()
                ))
                .toList();
    }

    //Listar o usuario pelo id
    public Optional<UserResponseDTO> findById(Long id) {
        return repository.findById(id)
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getTelephone()
                ));
    }

    //Atualizar o usuario se ele existir
    public UserResponseDTO updateUser(Long id, UserRegisterDTO dto) {

        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User updatedUser = repository.save(user);

        return new UserResponseDTO(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getTelephone()
        );
    }

    public void deleteUserById(Long id) {

        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Esse usuario não existe");
        }

        repository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
