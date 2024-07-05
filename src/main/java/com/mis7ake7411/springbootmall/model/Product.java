package com.mis7ake7411.springbootmall.model;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "product_name")
    private String productName;

    @NonNull
    private ProductCategory category;

    @NonNull
    @Column(name = "image_url")
    private String imageUrl;

    @NonNull
    private Integer price;

    @NonNull
    private Integer stock;

    private String description;

    @NonNull
    @Column(name = "created_date")
    private Date createdDate;

    @NonNull
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
