package com.mis7ake7411.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "user")
public class User {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NonNull
  private String email;

  @NonNull
  @JsonIgnore
  private String password;

  @NonNull
  @Column(name = "created_date")
  private Date createdDate;

  @NonNull
  @Column(name = "last_modified_date")
  private Date lastModifiedDate;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Order> orderList;
}
