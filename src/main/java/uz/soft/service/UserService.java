package uz.soft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.soft.dtos.user.StaffDto;
import uz.soft.dtos.user.UpdateUserdDto;
import uz.soft.entity.AuthUser;
import uz.soft.entity.Company;
import uz.soft.entity.Staff;
import uz.soft.entity.enums.UserRole;
import uz.soft.repository.CompanyRepository;
import uz.soft.repository.UserRepository;
import uz.soft.response.ApiResponse;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse createStaff(StaffDto staffDto) {
        char[] chars = staffDto.getPassport().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!(Character.isAlphabetic(chars[0]) && Character.isAlphabetic(chars[1]))) {
                return new ApiResponse("Passport series should be like AB1234567", false,404);
            }
            if (i > 1) {
                if (!Character.isDigit(chars[i])) {
                    return new ApiResponse("Passport series should be like AB1234567", false,404);
                }
            }
        }
        if (userRepository.existsByPassport(staffDto.getPassport())) {
            return new ApiResponse("This user already exists ", false,404);
        }
        Optional<Company> optionalCompany = companyRepository.findByName(staffDto.getCompanyName());
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Company not found" , false,404);
        }
        Company company = optionalCompany.get();
        Staff staff  =  new Staff();
        staff.setCompany(company);
        staff.setPassport(staffDto.getPassport());
        staff.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        staff.setUserRole(UserRole.STAFF);
        userRepository.save(staff);
        return new ApiResponse("User saved successfully", true,200);
    }

    public ApiResponse deleteStaff(String passNum) {
        Optional<AuthUser> authUserOptional = userRepository.findByPassport(passNum);
        if (authUserOptional.isEmpty()) {
            return new ApiResponse("User not found", false,404);
        }
        AuthUser authUser = authUserOptional.get();
        userRepository.delete(authUser);
        return new ApiResponse("User successfully deleted ", true,200);
    }
    public ApiResponse updateStaff(UpdateUserdDto dto) {
        Optional<AuthUser> optional = userRepository.findByPassport(dto.getPassport());
        AuthUser authUser = optional.get();
        authUser.setFullName(dto.getFullName());
        authUser.setTelNumber(dto.getTelephone());
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setPassport(dto.getPassport());
        userRepository.save(authUser);
        return new ApiResponse("Successfully updated" , true , 204);
    }

    public List<AuthUser> getAll() {
        List<AuthUser> all = userRepository.findAll();
        return all;    }
}
