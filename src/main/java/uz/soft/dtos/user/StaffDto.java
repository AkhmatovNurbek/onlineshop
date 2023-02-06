package uz.soft.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class StaffDto {
    @NotBlank(message = "Passport number cannot be blank!")
    @NotNull(message = "Passport number can not be null!")
    private String passport;
    @NotBlank(message = "Password can not be blank!")
    @NotNull(message = "Password  can not be null!")
    private String password;
    @NotBlank(message = "Company name can not be blank!")
    @NotNull(message = "Company name  can not be null!")
    private String companyName;

}
