package E_commerce.e_commerce.entitys.products.service;

import E_commerce.e_commerce.entitys.category.Category;
import E_commerce.e_commerce.entitys.category.repository.CategoryRepository;
import E_commerce.e_commerce.entitys.products.Product;
import E_commerce.e_commerce.entitys.products.productsDTO.ProductRegisterDTO;
import E_commerce.e_commerce.entitys.products.productsDTO.ProductResponseDTO;
import E_commerce.e_commerce.entitys.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    //Adicionar produto
    public Product addProduct(ProductRegisterDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryID())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityStock(dto.getQuantityStock());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        return productRepository.save(product);
    }

    //Listar produto
    public List<ProductResponseDTO> listProduct(){
      return productRepository.findAll()
              .stream()
              .map(product -> new ProductResponseDTO(
                      product.getId(),
                      product.getName(),
                      product.getDescription(),
                      product.getPrice(),
                      product.getQuantityStock(),
                      product.getImageUrl(),
                      product.getCategory().getName()
              ))
              .toList();
    }

    //Renomear produto
    public ProductResponseDTO updateProduct(Long id, ProductRegisterDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Category category = categoryRepository.findById(dto.getCategoryID())
                .orElseThrow(()-> new IllegalArgumentException("Categoria não encontrada"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityStock(dto.getQuantityStock());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        Product updateProduct = productRepository.save(product);

        return new ProductResponseDTO(
                updateProduct.getId(),
                updateProduct.getName(),
                updateProduct.getDescription(),
                updateProduct.getPrice(),
                updateProduct.getQuantityStock(),
                updateProduct.getImageUrl(),
                updateProduct.getCategory().getName()
        );
    }

    //Deletar produto
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
