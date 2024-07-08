package com.mis7ake7411.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "order_id")
  private Order order;

  @NotNull
  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "product_id")
  private Product product;

  @NonNull
  private Integer quantity;

  @NonNull
  private Integer amount;
}
