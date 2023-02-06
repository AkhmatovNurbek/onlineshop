package uz.soft.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.soft.entity.enums.ProductCategry;


import java.math.BigDecimal;

@Data
public class ProductUpdateDto {
    @NotNull(message = "Id can not be null!")
    private Long id;
    @NotBlank(message = "Name cannot be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;
    @NotBlank(message = "Price cannot be blank!")
    @NotNull(message = "Price can not be null!")
    private BigDecimal price;
    @NotBlank(message = "Amount cannot be blank!")
    @NotNull(message = "Amount can not be null!")
    private BigDecimal amount;
    @NotBlank(message = "Discount cannot be blank!")
    @NotNull(message = "Discount can not be null!")
    private BigDecimal discount;
    @NotBlank(message = "Category cannot be blank!")
    @NotNull(message = "Category can not be null!")
    private ProductCategry productCategry;
    @NotBlank(message = "Schedule months cannot be blank!")
    @NotNull(message = "Schedule months can not be null!")
    private Integer ScheduleMonths;
}
