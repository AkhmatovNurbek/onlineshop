package uz.soft.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.soft.dtos.product.FilterDto;
import uz.soft.dtos.product.ProductDto;
import uz.soft.dtos.product.ProductUpdateDto;
import uz.soft.entity.Product;
import uz.soft.entity.enums.ProductCategry;
import uz.soft.response.ApiResponse;
import uz.soft.service.ProductService;



import java.util.List;

@RestController
@SecurityRequirement(name = "Shop Bearer")
public class ProductController extends ApiController<ProductService>{
    public ProductController(ProductService service) {
        super(service);
    }
    @PostMapping(value = API+"/product/create")
    @PreAuthorize(value = "hasAnyRole('STAFF')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto dto){
        ApiResponse apiResponse = service.create(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping(value = API+"/product/get/{id}")
    @PreAuthorize(value = "hasAnyRole('STAFF','ADMIN','COSTUMER')")
    public ResponseEntity<Product> get(@PathVariable Long id ){
        Product one = service.getOne(id);
        return ResponseEntity.ok(one);
    }
    @PutMapping(value =API+"/product/update" )
    @PreAuthorize(value = "hasAnyRole('STAFF')")
    public ResponseEntity<?>update(@Valid @RequestBody ProductUpdateDto dto){
        ApiResponse apiResponse = service.update(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping(value = API+ "/product/delete/{id}")
    @PreAuthorize(value = "hasRole('STAFF')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyRole('STAFF','ADMIN','COSTUMER')")
    @GetMapping(value = API+"/product/getAll")
    public  ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PreAuthorize(value = "hasAnyRole('STAFF','ADMIN','COSTUMER')")
    @GetMapping(value = API+"/product/byCategory")
    public ResponseEntity<List<ProductDto>> searchByCategory(@RequestParam ProductCategry productCategry){
        return ResponseEntity.ok(service.searchByCategory(productCategry));
    }
    @PreAuthorize(value = "hasAnyRole('STAFF','ADMIN','COSTUMER')")
    @PostMapping(value = API+ "/product/filter")
    public ResponseEntity<List<ProductDto>> filterProd(@RequestBody FilterDto filterDto){
        return ResponseEntity.ok(service.filterProduct(filterDto));
    }
}