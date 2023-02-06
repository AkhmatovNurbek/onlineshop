package uz.soft.dtos.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDto {
    private Long productId;
    private BigDecimal amount;

}
