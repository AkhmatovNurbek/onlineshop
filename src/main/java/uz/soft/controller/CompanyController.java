package uz.soft.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.soft.dtos.company.CompanyDto;
import uz.soft.dtos.company.CompanyUpdateDto;
import uz.soft.response.ApiResponse;
import uz.soft.service.CompanyService;



@RestController
@SecurityRequirement(name = "Shop Bearer")
public class CompanyController extends ApiController<CompanyService>{
    public CompanyController(CompanyService service) {
        super(service);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value =  API+ "/company/new")
    public ResponseEntity<?> create(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = service.create(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @DeleteMapping(value = API+"/company/delete")
    public ResponseEntity<?> delete(Long id){
        ApiResponse apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = API+"/company/update")
    public ResponseEntity<?> update(@Valid @RequestBody CompanyUpdateDto companyDto){
        ApiResponse ap = service.update(companyDto);
        return ResponseEntity.status(ap.isSuccess()?200:409).body(ap);
    }
}
