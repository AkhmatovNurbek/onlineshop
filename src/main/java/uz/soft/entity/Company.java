package uz.soft.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.soft.entity.template.AbLongEntity;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company extends AbLongEntity {
    @Column(unique = true , nullable = false)
    private String name;
    private String address;

}
