package uz.soft.dtos.company;

import lombok.Data;

@Data
public class CompanyUpdateDto {
    private Long id ;
    private String name;
    private String address;
}
