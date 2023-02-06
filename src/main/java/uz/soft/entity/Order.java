package uz.soft.entity;


import jakarta.persistence.*;
import lombok.*;
import uz.soft.entity.template.AbsMainEntity;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order extends AbsMainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer orderNum = 0;
//    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private BigDecimal amount;
    private BigDecimal totalPrice ;
    private Integer creditMonths;

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        orderNum++;
        this.orderNum = orderNum;
    }
}
