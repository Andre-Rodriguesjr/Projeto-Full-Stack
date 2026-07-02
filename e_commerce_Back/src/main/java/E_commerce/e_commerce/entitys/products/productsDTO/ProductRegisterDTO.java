package E_commerce.e_commerce.entitys.products.productsDTO;

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
public class ProductRegisterDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantityStock;
    private String imageUrl;
    private Long categoryID;
}
