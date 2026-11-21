package E_commerce.e_commerce.entitys.product.repository;

import E_commerce.e_commerce.entitys.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findByCategoryId(Long categoryId);

}
