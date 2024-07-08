package com.mis7ake7411.springbootmall.controller;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import com.mis7ake7411.springbootmall.dto.ProductDto;
import com.mis7ake7411.springbootmall.dto.ProductQueryParams;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.service.ProductService;
import com.mis7ake7411.springbootmall.util.Page;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<Page<Product>> getProducts(
      // 查詢條件 Filtering
      @RequestParam(required = false) ProductCategory category,
      @RequestParam(required = false) String search,
      // 排序 Sorting
      @RequestParam(required = false) String[] orderBy,
      @RequestParam(required = false) String[] sort,
      // 分頁 Pagination
      @RequestParam(defaultValue = "5") @Min(0) @Max(1000) Integer limit,
      @RequestParam(defaultValue = "0") @Min(0) Integer offset) {
    ProductQueryParams queryParams = ProductQueryParams.builder()
                                                       .category(category)
                                                       .search(search)
                                                       .orderBy(orderBy)
                                                       .sort(sort)
                                                       .limit(limit)
                                                       .offset(offset)
                                                       .build();
    Integer count = productService.getProductsCount(queryParams);
    List<Product> list = productService.getProducts(queryParams);
    Page<Product> page = Page.<Product>builder()
                             .limit(limit)
                             .offset(offset)
                             .total(count)
                             .result(list)
                             .build();
    return ResponseEntity.status(HttpStatus.OK)
                         .body(page);
  }

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
  public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto productDto) {
    Integer productId = productService.createProduct(productDto);
    Product product = productService.getProductById(productId);

    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(product);
  }

  @PutMapping("/products/{productId}")
  public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
      @RequestBody ProductDto productDto) {
    Product product = productService.getProductById(productId);
    if (product == null) {
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
