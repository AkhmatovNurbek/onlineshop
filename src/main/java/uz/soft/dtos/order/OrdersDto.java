package uz.soft.dtos.order;

import lombok.*;
import uz.soft.dtos.product.ProductDto;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDto {
    private LocalDate ordered;

    private ProductDto productDto;
    private Integer orderNum;
    private HashMap<LocalDate , BigDecimal> creditTable;
}
