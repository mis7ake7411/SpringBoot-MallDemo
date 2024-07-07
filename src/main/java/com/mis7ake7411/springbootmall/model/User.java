package com.mis7ake7411.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  @JsonIgnore
  private String email;

  @NonNull
  private String password;

  @NonNull
  @Column(name = "created_date")
  private Date createdDate;

  @NonNull
  @Column(name = "last_modified_date")
  private Date lastModifiedDate;
}
