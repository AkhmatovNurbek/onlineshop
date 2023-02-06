package uz.soft.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.soft.dtos.order.OrderCreateDto;
import uz.soft.dtos.order.OrdersDto;
import uz.soft.service.OrderService;




@RestController
@SecurityRequirement(name = "Shop Bearer")
public class OrderController extends ApiController<OrderService>{
    public OrderController(OrderService service) {
        super(service);
    }
    @PostMapping(value = API + "/order/new" )
    @PreAuthorize(value = "hasAnyRole('ADMIN','COSTUMER')")
    public ResponseEntity<?> create(@Valid @RequestBody OrderCreateDto dto){
        OrdersDto order = service.createOrder(dto);
        return ResponseEntity.ok(order);
    }
}
