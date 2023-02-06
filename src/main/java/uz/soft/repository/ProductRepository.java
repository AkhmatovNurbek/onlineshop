package uz.soft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import uz.soft.dtos.product.FilterDto;
import uz.soft.dtos.product.ProductDto;
import uz.soft.entity.Product;
import uz.soft.entity.enums.ProductCategry;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("from Product where id = :id")
    Optional<Product> get(Long id);

    @Query(value = " select * from Product ", nativeQuery = true)
    Optional<List<Product>> getAll();

    Optional<List<Product>> findByProductCategry(ProductCategry search);

    @Query(value = "SELECT new uz.soft.dtos.product.ProductDto(p.name , p.price, p.amount,p.discount, p.productCategry,p.companyName,p.schedule) from Product  p where p.name LIKE CONCAT('%', :name, '%')\n" +
            " AND p.price >= :minPrice\n" +
            " AND p.price <= :maxPrice" )
    List<ProductDto>  findByPriceAndName( @Param("name") String name ,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
}
