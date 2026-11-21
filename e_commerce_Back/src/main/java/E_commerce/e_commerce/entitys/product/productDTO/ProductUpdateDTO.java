package E_commerce.e_commerce.entitys.product.productDTO;

import E_commerce.e_commerce.entitys.category.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {

    @NotBlank
    @Size(min = 3, max = 50)
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