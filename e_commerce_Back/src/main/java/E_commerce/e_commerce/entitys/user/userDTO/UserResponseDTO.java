package E_commerce.e_commerce.entitys.user.userDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String telephone;


}
