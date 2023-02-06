package uz.soft.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.soft.entity.enums.ProductCategry;
import uz.soft.entity.template.AbLongEntity;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbLongEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private BigDecimal amount;
    private BigDecimal discount ;
    @Enumerated(EnumType.STRING)
    private ProductCategry productCategry;
    @Column(nullable = false)
    private String companyName;
    @Column
    private Integer schedule;
}
