package id.hadiyan.productservice.service;

import id.hadiyan.commonservice.dto.product.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    Page<ProductDto> getAllProducts(Pageable pageable);
    ProductDto getProductById(UUID id);
    void saveProduct(ProductDto productDto);
    void updateProduct(UUID id, ProductDto productDto);
}
