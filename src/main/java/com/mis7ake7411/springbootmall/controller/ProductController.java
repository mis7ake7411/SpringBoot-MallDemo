package com.mis7ake7411.springbootmall.controller;

import com.mis7ake7411.springbootmall.dto.ProductDto;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Integer productId = productService.createProduct(productDto);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(product);
    }

    @PutMapping("/products/{productId}")
    public  ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto) {
        Product product = productService.getProductById(productId);
        if (product == null){
            return ResponseEntity.notFound()
                                 .build();
        }

        productService.updateProduct(productId, productDto);
        Product updProduct = productService.getProductById(productId);

        return ResponseEntity.ok(updProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .build();
    }
}
