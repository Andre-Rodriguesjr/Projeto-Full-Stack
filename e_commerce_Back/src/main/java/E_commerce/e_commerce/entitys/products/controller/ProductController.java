package E_commerce.e_commerce.entitys.products.controller;

import E_commerce.e_commerce.entitys.products.productsDTO.ProductRegisterDTO;
import E_commerce.e_commerce.entitys.products.productsDTO.ProductResponseDTO;
import E_commerce.e_commerce.entitys.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Criar / Adicionar produtos
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRegisterDTO dto) {
        ProductResponseDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }

    //Achar produto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    //Deletar produto pelo ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    //Update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRegisterDTO dto) {
        ProductResponseDTO product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(product);
    }
}
