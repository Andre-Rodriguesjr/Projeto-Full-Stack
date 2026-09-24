package E_commerce.e_commerce.entitys.user.Service;

import E_commerce.e_commerce.entitys.user.Repository.UserRepository;
import E_commerce.e_commerce.entitys.user.User;
import E_commerce.e_commerce.entitys.user.userDTO.UserLoginDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserRegisterDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserResponseDTO;
import E_commerce.e_commerce.entitys.user.userDTO.UserUpdateDTO;
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

    // Criar usuário
    public UserResponseDTO createUser(UserRegisterDTO dto) {
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
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = repository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getTelephone()
        );
    }

    // Listar todos os usuários
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

    // Listar o usuário pelo ID
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

    // Atualizar o usuário se ele existir
    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado")
                );

        // Verifica se o novo e-mail já pertence a outro usuário
        if (!user.getEmail().equalsIgnoreCase(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {

            throw new IllegalArgumentException(
                    "Esse e-mail já está em uso por outro usuário"
            );
        }

        // Verifica se o novo username já pertence a outro usuário
        if (!user.getUsername().equalsIgnoreCase(dto.getUsername())
                && repository.existsByUsername(dto.getUsername())) {

            throw new IllegalArgumentException(
                    "Esse nome de usuário já está em uso por outro usuário"
            );
        }

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setTelephone(dto.getTelephone());

        // Só altera a senha se o usuário informou uma nova
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {

            user.setPassword(
                    passwordEncoder.encode(dto.getPassword())
            );
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
            throw new IllegalArgumentException("Esse usuário não existe");
        }
        repository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserResponseDTO login(UserLoginDTO dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Email ou senha inválidos")
                );

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Email ou senha inválidos");
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getTelephone()
        );

    }
}