package E_commerce.e_commerce.entitys.product.controller;

import E_commerce.e_commerce.entitys.product.productDTO.ProductRegistreDTO;
import E_commerce.e_commerce.entitys.product.productDTO.ProductResponseDTO;
import E_commerce.e_commerce.entitys.product.productDTO.ProductUpdateDTO;
import E_commerce.e_commerce.entitys.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permite requisições do Live Server
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Criar Produto
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRegistreDTO dto){
        return ResponseEntity.ok(productService.addProduct(dto));
    }

    //Buscar producto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProduct(@PathVariable Long id){
        return productService.findById(id)
                .map(dto->ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProductResponseDTO>  listProduct(){
        return productService.listAllProducts();
    }

    //Atualizar Produtos
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateDTO dto){
        try {
            return ResponseEntity.ok(productService.updateProduct(id, dto));
        } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Deletar Produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id ){
        productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    //Achar produto pelo filtro/categoria
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> findByCategory(@PathVariable Long categoryId){
        List<ProductResponseDTO> list = productService.findByCategory(categoryId);
        return ResponseEntity.ok(list);
    }
}
