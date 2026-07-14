package E_commerce.e_commerce.entitys.products.service;

import E_commerce.e_commerce.entitys.category.Category;
import E_commerce.e_commerce.entitys.category.repository.CategoryRepository;
import E_commerce.e_commerce.entitys.products.Product;
import E_commerce.e_commerce.entitys.products.productsDTO.ProductRegisterDTO;
import E_commerce.e_commerce.entitys.products.productsDTO.ProductResponseDTO;
import E_commerce.e_commerce.entitys.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Criar produto
    public ProductResponseDTO createProduct(ProductRegisterDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityStock(dto.getQuantityStock());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return convertToDTO(savedProduct);
    }

    // Listar produtos
    public List<ProductResponseDTO> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Buscar produto por id
    public Optional<ProductResponseDTO> findById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Atualizar produto
    public ProductResponseDTO updateProduct(Long id, ProductRegisterDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityStock(dto.getQuantityStock());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return convertToDTO(updatedProduct);
    }

    // Deletar produto
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        productRepository.deleteById(id);
    }

    // Converter Entity para DTO
    private ProductResponseDTO convertToDTO(Product product) {

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantityStock(),
                product.getImageUrl(),
                product.getCategory().getName()
        );
    }
}
