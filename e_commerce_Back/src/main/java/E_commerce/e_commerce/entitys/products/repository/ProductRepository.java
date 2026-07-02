package E_commerce.e_commerce.entitys.products.repository;

import E_commerce.e_commerce.entitys.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
}
