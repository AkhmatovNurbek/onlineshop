package uz.soft.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.soft.dtos.user.StaffDto;
import uz.soft.dtos.user.UpdateUserdDto;
import uz.soft.response.ApiResponse;
import uz.soft.service.UserService;




@RestController
@SecurityRequirement(name = "Shop Bearer")
public class AdminController extends ApiController<UserService> {


    public AdminController(UserService service) {
        super(service);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(path = "/staff/new")
    public ResponseEntity<?> createUser(@Valid @RequestBody StaffDto staffDto){
        ApiResponse apiResponse = service.createStaff(staffDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/staff/delete")
    public ResponseEntity<?>deleteUser(@Valid @RequestBody String passNum){
        ApiResponse apiResponse = service.deleteStaff(passNum);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(path = "/staff/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserdDto dto){
        ApiResponse apiResponse = service.updateStaff(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(path = "/user/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
