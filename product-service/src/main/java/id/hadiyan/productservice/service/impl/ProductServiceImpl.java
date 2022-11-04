package id.hadiyan.productservice.service.impl;

import id.hadiyan.commonservice.constant.KafkaTopic;
import id.hadiyan.commonservice.dto.product.ProductDto;
import id.hadiyan.commonservice.util.JsonUtil;
import id.hadiyan.productservice.entity.Product;
import id.hadiyan.productservice.repository.ProductRepository;
import id.hadiyan.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final JsonUtil jsonUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductDto> productDtoList = productPage.getContent().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return new PageImpl<>(productDtoList, pageable, productPage.getTotalElements());
    }

    @Override
    public ProductDto getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        log.info("product dto is {}", productDto);
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        modelMapper.map(product, productDto);
        produce(productDto);
    }

    @Override
    public void updateProduct(UUID id, ProductDto productDto) {
        log.info("product id is {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        modelMapper.map(productDto, product);
        productRepository.save(product);
        modelMapper.map(product, productDto);
        produce(productDto);
    }

    private void produce(ProductDto productDto) {
        String jsonString = jsonUtil.objectToString(productDto);
        String key = String.format("product:%s", productDto.getId());
        kafkaTemplate.send(KafkaTopic.Product.PRODUCT_UPDATE, key, jsonString);
    }

    @KafkaListener(topics = KafkaTopic.Product.PRODUCT_UPDATE)
    private void listenSelf(String message){
        ProductDto productDto = jsonUtil.stringToObject(message, ProductDto.class);
        log.info("Received message: {}", message);
    }
}
