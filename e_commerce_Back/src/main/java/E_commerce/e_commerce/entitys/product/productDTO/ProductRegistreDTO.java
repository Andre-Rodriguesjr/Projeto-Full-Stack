package E_commerce.e_commerce.entitys.product.productDTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRegistreDTO {

    @NotBlank
    @Size(min = 3, max = 50, message = "O nome do produto deve ter entre 3 a 50 caracteres!")
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String imageURL;

    @NotNull
    private Long category;

}
