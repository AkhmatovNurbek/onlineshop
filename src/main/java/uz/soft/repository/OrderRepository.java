package uz.soft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.soft.entity.Order;


import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order , Long> {

    Optional<Order>getOrderByUserId(Long id);
}
