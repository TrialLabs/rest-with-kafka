package id.hadiyan.productservice.controller;

import id.hadiyan.commonservice.dto.BaseResponse;
import id.hadiyan.commonservice.dto.product.ProductDto;
import id.hadiyan.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts(Pageable pageable) {
        log.info("Get all products");
        BaseResponse response = BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .data(productService.getAllProducts(pageable))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID id) {
        BaseResponse response = BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .data(productService.getProductById(id))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
        BaseResponse response = BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .data(productDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDto productDto){
        productDto.setId(id);
        productService.updateProduct(id, productDto);
        BaseResponse response = BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .data(productDto)
                .build();
        return ResponseEntity.ok(response);
    }
}
