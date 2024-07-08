package com.mis7ake7411.springbootmall.model;

import java.util.Date;
import java.util.List;
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
@Table(name = "order")
public class Order {
  @Id
  @Column(name = "order_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Column(name = "user_id")
  private Integer userId;

  @NotNull
  @Column(name = "total_amount")
  private Integer totalAmount;

  @NotNull
  @Column(name = "created_date")
  private Date createdDate;

  @NotNull
  @Column(name = "last_modified_date")
  private Date lastModifiedDate;

  @Transient
  private List<OrderItem> orderItemList;
}
