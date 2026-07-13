package E_commerce.e_commerce.entitys.products.controller;

import E_commerce.e_commerce.entitys.products.Product;
import E_commerce.e_commerce.entitys.products.productsDTO.ProductRegisterDTO;
import E_commerce.e_commerce.entitys.products.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producties")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRegisterDTO dto) {
        return productService.createProduct(dto);
    }


}
