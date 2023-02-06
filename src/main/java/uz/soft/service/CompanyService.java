package uz.soft.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.soft.dtos.company.CompanyDto;
import uz.soft.dtos.company.CompanyUpdateDto;
import uz.soft.entity.Company;
import uz.soft.exceptions.GenericNotFoundException;
import uz.soft.repository.CompanyRepository;
import uz.soft.response.ApiResponse;


import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final AuthService authService;
    public ApiResponse create(CompanyDto companyDto) {
        Optional<Company> optional = companyRepository.findByName(companyDto.getName());

        if (optional.isPresent()) {
            return new ApiResponse("This Company already exists " , false,404);
        }
        Company newCompany = new Company();
        newCompany.setName(companyDto.getName());
        newCompany.setAddress(companyDto.getAddress());
        newCompany.setCreatedBy(authService.getCurrentUser().getId());
        newCompany.setCreatedAt(LocalDate.now());
        companyRepository.save(newCompany);
        return new ApiResponse("Successfully created", true,200);
    }

    public ApiResponse delete(Long id) {
        companyRepository.deleteById(id);
        return new ApiResponse("Company deleted", true,200);   }

    public ApiResponse update(CompanyUpdateDto companyDto) {
        Company company = getOne(companyDto.getId());
        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());
        company.setUpdatedAt(LocalDate.now());
        company.setUpdatedBy(authService.getCurrentUser().getId());
        company.setUpdatedAt(LocalDate.now());
        companyRepository.save(company);
        return new ApiResponse("Company updated " , true,200);
    }
    public Company getOne(@NonNull Long id ){
         return  companyRepository.findById(id).
                orElseThrow(()-> new GenericNotFoundException("Company not found" , 404));
    }
}