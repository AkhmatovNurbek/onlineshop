package uz.soft.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class UpdateUserdDto {
    @NotBlank(message = "Passport number cannot be blank!")
    @NotNull(message = "Passport number can not be null!")
    private String passport;
    @NotBlank(message = "Password cannot be blank!")
    @NotNull(message = "Password can not be null!")
    private String password;
    @NotBlank(message = "Fullname cannot be blank!")
    @NotNull(message = "Fullname can not be null!")
    private String fullName;
    @NotBlank(message = "Telephone cannot be blank!")
    @NotNull(message = "Telephone can not be null!")
    private Integer telephone;


}

