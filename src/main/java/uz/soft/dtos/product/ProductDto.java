package uz.soft.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.soft.entity.Product;
import uz.soft.entity.enums.ProductCategry;

import java.math.BigDecimal;
@Builder
@NoArgsConstructor
@Data
public class ProductDto {
    @NotBlank(message = "Name cannot be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;
    @NotNull(message = "Price can not be null!")
    private BigDecimal price;
    @NotNull(message = "Amount can not be null!")
    private BigDecimal amount;
    @NotBlank(message = "Discount cannot be blank!")
    @NotNull(message = "Discount can not be null!")
    private BigDecimal discount;
    @NotBlank(message = "Category cannot be blank!")
    @NotNull(message = "Category can not be null!")
    private ProductCategry productCategry;
    @NotBlank(message = "Company name cannot be blank!")
    @NotNull(message = "Company name can not be null!")
    private String companyName;
    @NotBlank(message = "Schedule months cannot be blank!")
    @NotNull(message = "Schedule months can not be null!")
    private Integer ScheduleMonths;

//    public ProductDto(Product product) {
//        this.name = product.getName();
//        this.price = product.getPrice();
//        this.amount = product.getAmount();
//        this.discount = product.getDiscount();
//        this.productCategry = product.getProductCategry().name();
//        this.companyName = product.getCompanyName();
//        this.ScheduleMonths = product.getSchedule();
//    }

    public ProductDto(String name, BigDecimal price, BigDecimal amount, BigDecimal discount, ProductCategry productCategry, String companyName, Integer scheduleMonths) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.discount = discount;
        this.productCategry = productCategry;
        this.companyName = companyName;
        ScheduleMonths = scheduleMonths;
    }
}
