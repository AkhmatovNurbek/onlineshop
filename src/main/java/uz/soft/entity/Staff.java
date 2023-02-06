package uz.soft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;




@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff extends AuthUser{
    @OneToOne
    private Company company;
}

