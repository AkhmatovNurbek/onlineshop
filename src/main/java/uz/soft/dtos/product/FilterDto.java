package uz.soft.dtos.product;

import lombok.Data;


import java.math.BigDecimal;

@Data
public class FilterDto {

    private String  name = "";

    private BigDecimal minPrice = new BigDecimal(0) ;

    private BigDecimal maxPrice = new BigDecimal(1000000000) ;
}
