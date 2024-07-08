package com.mis7ake7411.springbootmall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
  @Id
  @Column(name = "order_item_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Column(name = "order_id")
  private Integer orderId;

  @NotNull
  @Column(name = "product_id")
  private Integer productId;

  @NotNull
  private Integer quantity;

  @NotNull
  private Integer amount;

  @Transient
  private String productName;

  @Transient
  private String imageUrl;
}
