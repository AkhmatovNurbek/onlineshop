package uz.soft.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.soft.dtos.product.FilterDto;
import uz.soft.dtos.product.ProductDto;
import uz.soft.dtos.product.ProductUpdateDto;
import uz.soft.entity.Product;
import uz.soft.entity.enums.ProductCategry;
import uz.soft.exceptions.GenericNotFoundException;
import uz.soft.repository.ProductRepository;
import uz.soft.repository.UserRepository;
import uz.soft.response.ApiResponse;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final AuthService authService;
    private final UserRepository userRepository;


    public ApiResponse create(@NonNull ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        product.setDiscount(dto.getDiscount());
        product.setProductCategry(dto.getProductCategry());
        product.setCompanyName(dto.getCompanyName());
        product.setSchedule(dto.getScheduleMonths());
        product.setCreatedBy(authService.getCurrentUser().getId());
        product.setCreatedAt(LocalDate.now());
        productRepository.save(product);
        return new ApiResponse("Product saved ", true, 200);
    }

    public ApiResponse delete(@NonNull Long id) {
        productRepository.deleteById(id);
        return new ApiResponse("Product deleted", true, 200);
    }

    public ApiResponse update(@NonNull ProductUpdateDto dto) {
        Product product1 = getOne(dto.getId());
        product1.setName(dto.getName());
        product1.setAmount(dto.getAmount());
        product1.setPrice(dto.getPrice());
        product1.setDiscount(dto.getDiscount());
        product1.setProductCategry(dto.getProductCategry());
        product1.setSchedule(dto.getScheduleMonths());
        product1.setUpdatedAt(LocalDate.now());
        product1.setUpdatedBy(authService.getCurrentUser().getId());
        productRepository.save(product1);
        return new ApiResponse("Product saved", true, 200);
    }

    public Product getOne(@NonNull Long id) {
        return productRepository.get(id).orElseThrow(() -> new GenericNotFoundException("Not Found", 404));
    }

    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.getAll().orElseThrow(() -> {
            throw new GenericNotFoundException("Product not found ", 404);
        }).forEach(product -> productDtos.add(this.getDto(product.getId())));
        return productDtos;
    }

    public ProductDto getDto(@NonNull Long id) {
        Product product = productRepository.get(id).orElseThrow(() -> new GenericNotFoundException("Not Found", 404));
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setAmount(product.getAmount());
        productDto.setCompanyName(product.getCompanyName());
        productDto.setProductCategry(product.getProductCategry());
        productDto.setDiscount(product.getDiscount());
        productDto.setScheduleMonths(product.getSchedule());
        return productDto;
    }


    public List<ProductDto> searchByCategory(ProductCategry productCategry) {
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.findByProductCategry(productCategry).orElseThrow(() -> {
            throw new GenericNotFoundException("Product Not found", 404);
        }).forEach(product -> productDtos.add(this.getDto(product.getId())));
        return productDtos;
    }

    public List<ProductDto> filterProduct(FilterDto filterDto) {
        List<ProductDto> productDtos = new ArrayList<>();
     return productRepository.findByPriceAndName(filterDto.getName(),filterDto.getMinPrice(),filterDto.getMaxPrice());
//        for (Product product : allProduct) {
//            if (filterDto.getName().equalsIgnoreCase("")) {
//                if (product.getPrice().compareTo(filterDto.getMinPrice()) >= 0 && product.getPrice().compareTo(filterDto.getMaxPrice()) <= 0) {
//                    productDtos.add(this.getDto(product.getId()));
//                }
//            }else {
//                if (product.getPrice().compareTo(filterDto.getMinPrice()) >= 0 && product.getPrice().compareTo(filterDto.getMaxPrice()) <= 0) {
//                    productDtos.add(this.getDto(product.getId()));
//                }
//            }
//
//        }

    }
}
