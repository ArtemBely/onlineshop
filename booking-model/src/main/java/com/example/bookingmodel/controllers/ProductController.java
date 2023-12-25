package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.ProductDTO;
import com.example.bookingmodel.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController("productController")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }


    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        log.info("Start to retrieve all products...");
        return productService.findAllProducts();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/products/{id}/photo")
    public ResponseEntity<Void> uploadProductPhoto(@PathVariable("id") int userId,
                                                @RequestParam("photo") MultipartFile photo) throws IOException {
        byte[] photoBytes = photo.getBytes();
        productService.saveProductPhoto(userId, photoBytes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{id}/photo")
    public ResponseEntity<byte[]> getProductPhoto(@PathVariable int id) {
        byte[] photoBytes = productService.getProductPhoto(id);
        if (photoBytes == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(photoBytes, headers, HttpStatus.OK);
    }
}
