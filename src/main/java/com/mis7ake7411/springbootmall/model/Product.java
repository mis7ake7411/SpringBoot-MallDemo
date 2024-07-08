package com.mis7ake7411.springbootmall.model;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;

    @NotNull
    @Column(name = "created_date")
    private Date createdDate;

    @NotNull
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
