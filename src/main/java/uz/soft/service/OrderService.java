package uz.soft.service;


import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import uz.soft.dtos.order.OrderCreateDto;
import uz.soft.dtos.order.OrdersDto;
import uz.soft.dtos.product.ProductDto;
import uz.soft.entity.Order;
import uz.soft.entity.Product;
import uz.soft.repository.OrderRepository;
import uz.soft.repository.ProductRepository;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final AuthService authService;
    private final ProductRepository productRepository;

    public OrdersDto createOrder(@NotNull OrderCreateDto dto) {
        BigDecimal a = new BigDecimal(100);
        Optional<Product> optionalProduct = productRepository.get(dto.getProductId());
        Product product = optionalProduct.get();
        int i = product.getAmount().compareTo(dto.getAmount());
        if(i==-1){
           return new OrdersDto();
        }
        Order order  = new Order();
        order.setProductId(product.getId());
        order.setAmount(dto.getAmount());
        order.setCreditMonths(product.getSchedule());
        BigDecimal total = (product.getPrice().multiply(dto.getAmount())).subtract((product.getPrice().multiply(dto.getAmount().multiply(product.getDiscount()))).divide(a, 10, RoundingMode.HALF_UP));
        order.setTotalPrice(total);
        order.setOrderNum(order.getOrderNum());
        order.setCreatedAt(LocalDate.now());
        order.setCreatedBy(authService.getCurrentUser().getId());
        order.setUserId(authService.getCurrentUser().getId());
        Order order1 = orderRepository.save(order);
        HashMap<LocalDate, BigDecimal> creditTable = creditTable(order1);
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setOrderNum(order.getOrderNum());
        ProductDto dto1 = productService.getDto(product.getId());
        dto1.setAmount(dto.getAmount());
        ordersDto.setProductDto(dto1);
        ordersDto.setOrdered(LocalDate.now());
        ordersDto.setCreditTable(creditTable);
        product.setAmount(product.getAmount().subtract(dto.getAmount()));
        productRepository.save(product);
        return ordersDto;
    }
    protected HashMap<LocalDate , BigDecimal> creditTable(Order order){
        HashMap<LocalDate , BigDecimal> grafik = new HashMap<>();
        BigDecimal monthly = order.getTotalPrice().divide(BigDecimal.valueOf(order.getCreditMonths()),10,RoundingMode.HALF_UP);

        for (int i = 1; i <=order.getCreditMonths() ; i++) {
            grafik.put(LocalDate.now().plusMonths(i), monthly);
        }
        return grafik;
    }

}
