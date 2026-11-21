package E_commerce.e_commerce.entitys.product.productDTO;

import E_commerce.e_commerce.entitys.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String imageURL;
    private Category category;

}
