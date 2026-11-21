package E_commerce.e_commerce.entitys.product.service;

import E_commerce.e_commerce.entitys.category.Category;
import E_commerce.e_commerce.entitys.category.repository.CategoryRepository;
import E_commerce.e_commerce.entitys.product.productDTO.ProductRegistreDTO;
import E_commerce.e_commerce.entitys.product.productDTO.ProductResponseDTO;
import E_commerce.e_commerce.entitys.product.productDTO.ProductUpdateDTO;
import E_commerce.e_commerce.entitys.product.repository.ProductRepository;
import E_commerce.e_commerce.entitys.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    //Adicionar produto
    public ProductResponseDTO addProduct(ProductRegistreDTO dto) {

        if (repository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Já existe um produto cadastrado com esse nome!");
        }

        Category category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));


        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setImageURL(dto.getImageURL());
        product.setCategory(category);

        Product savedProduct = repository.save(product);

        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getQuantity(),
                savedProduct.getPrice(),
                savedProduct.getImageURL(),
                savedProduct.getCategory()
        );
    }


    //Listar todos os produtos
    public List<ProductResponseDTO> listAllProducts() {
        return repository.findAll().stream().map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getImageURL(),
                product.getCategory()
        )).toList();
    }

    // Listar o produto pelo ID
    public Optional<ProductResponseDTO> findById(Long id) {
        return repository.findById(id)
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getImageURL(),
                        product.getCategory()
                ));
    }

    //Atualiza produto
    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO dto) {
        Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

        if (!product.getName().equalsIgnoreCase(dto.getName()) && repository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Esse nome já pertence a um produto, use outro nome!");
        }

        Category category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setImageURL(dto.getImageURL());
        product.setCategory(category);

        Product updateProduct = repository.save(product);

        return new ProductResponseDTO(
                updateProduct.getId(),
                updateProduct.getName(),
                updateProduct.getDescription(),
                updateProduct.getQuantity(),
                updateProduct.getPrice(),
                updateProduct.getImageURL(),
                updateProduct.getCategory()
        );
    }


    //Deletar produto pelo Id
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Esse produto não existe!");
        }
        repository.deleteById(id);
    }

    //Achar produtos pela categoria
    public List<ProductResponseDTO> findByCategory(Long categoryId) {
        List<Product> products = repository.findByCategoryId(categoryId);

        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getImageURL(),
                        product.getCategory()
                )).toList();
    }


}
