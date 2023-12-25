package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.ProductDTO;
import com.example.bookingmodel.data.entity.CustomerEntity;
import com.example.bookingmodel.data.entity.ProductEntity;
import com.example.bookingmodel.data.mapper.ProductMapper;
import com.example.bookingmodel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductDTO addProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.mapToEntity(productDTO);
        productEntity = productRepository.save(productEntity);
        return productMapper.mapToDto(productEntity);
    }

    public List<ProductDTO> findAllProducts() {
        log.info("Products finding...");
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

    public ProductDTO getProductById(int productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return productMapper.mapToDto(productEntity);
    }

    @Transactional
    public void saveProductPhoto(int productId, byte[] photoBytes) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setPhoto(photoBytes);
        productRepository.save(product);
    }

    public byte[] getProductPhoto(int productId) {
        return productRepository.findById(productId)
                .map(ProductEntity::getPhoto)
                .orElse(null);
    }
}
