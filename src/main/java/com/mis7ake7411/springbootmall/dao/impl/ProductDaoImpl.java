package com.mis7ake7411.springbootmall.dao.impl;

import com.mis7ake7411.springbootmall.dao.ProductDao;
import com.mis7ake7411.springbootmall.dto.ProductDto;
import com.mis7ake7411.springbootmall.dto.ProductQueryParams;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  Date now = new Date();

  @Override
  public Integer getProductsCount(ProductQueryParams queryParams) {
    StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product WHERE 1=1 ");
    Map<String, Object> params = new HashMap<String, Object>();
    // 查詢條件 Filtering
    queryParamsFilter(sql, params, queryParams);

    return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
  }

  @Override
  public List<Product> getProducts(ProductQueryParams queryParams) {
    StringBuilder sql = new StringBuilder(
        "SELECT product_id, product_name, category, image_url, " +
        "price, stock, description, created_date, last_modified_date " +
        "FROM product WHERE 1=1 ");
    Map<String, Object> params = new HashMap<String, Object>();
    // 查詢條件 Filtering
    queryParamsFilter(sql, params, queryParams);
    // 排序 Sorting
    if (queryParams.getOrderBy() != null && queryParams.getOrderBy().length > 0) {
      sql.append(" ORDER BY ");
      for (int i = 0; i < queryParams.getOrderBy().length; i++) {
        sql.append(queryParams.getOrderBy()[i])
            .append(" ")
            .append(queryParams.getSort() != null && queryParams.getSort().length > i
                ? queryParams.getSort()[i] : "DESC");
        if (i < queryParams.getOrderBy().length - 1) {
          sql.append(", ");
        }
      }
    } else {
      sql.append(" ORDER BY created_date DESC");
    }
    // 分頁 Pagination
    sql.append(" LIMIT :limit OFFSET :offset");
    params.put("limit", queryParams.getLimit());
    params.put("offset", queryParams.getOffset());

    return namedParameterJdbcTemplate.query(sql.toString(), params, new ProductRowMapper());
  }

  @Override
  public Product getProductById(Integer id) {
    String sql = "SELECT product_id, product_name, category, image_url, " +
        "price, stock, description, created_date, last_modified_date " +
        "FROM product WHERE product_id = :id";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);

    List<Product> query = namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());
    if (!query.isEmpty()) {
      return query.get(0);
    }

    return null;
  }

  @Override
  public Integer createProduct(ProductDto productDto) {
    StringBuilder sql = new StringBuilder(
        "INSERT INTO product(product_name, category, image_url, " +
            "price, stock, description, created_date, last_modified_date) " +
            "VALUES (:productName, :category, :imageUrl, :price, :stock, " +
            ":description, :createdDate, :lastModifiedDate)");
    Map<String, Object> params = new HashMap<>();
    params.put("createdDate", now);
    addDataParams(sql, params, productDto);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(params), keyHolder);

    return keyHolder.getKey()
                    .intValue();
  }

  @Override
  public void updateProduct(Integer id, ProductDto productDto) {
    StringBuilder sql = new StringBuilder(
        "UPDATE product SET product_name =:productName, category =:category, image_url =:imageUrl, " +
            "price =:price, stock =:stock, description =:description, last_modified_date =:lastModifiedDate "+
            "WHERE product_id = :id");
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    addDataParams(sql, params, productDto);
    namedParameterJdbcTemplate.update(sql.toString(), params);
  }

  @Override
  public void deleteProductById(Integer id) {
    String sql = "DELETE FROM product WHERE product_id = :id";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    namedParameterJdbcTemplate.update(sql, params);
  }

  @Override
  public void updateStock(Integer id, Integer stock) {
    StringBuilder sql = new StringBuilder(
        "UPDATE product SET stock =:stock, last_modified_date =:lastModifiedDate"
            + " WHERE product_id = :id"
    );
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("stock", stock);
    params.put("lastModifiedDate", now);
    namedParameterJdbcTemplate.update(sql.toString(), params);
  }

  private void queryParamsFilter(StringBuilder sql, Map<String, Object> params, ProductQueryParams queryParams){
    if (queryParams.getCategory() != null) {
      sql.append("AND category = :category ");
      params.put("category", queryParams.getCategory().name());
    }
    if (queryParams.getSearch() != null) {
      sql.append("AND UPPER(product_name) LIKE :search ");
      params.put("search", "%" + queryParams.getSearch().toUpperCase() + "%");
    }
  }

  private void addDataParams(StringBuilder sql, Map<String, Object> params, ProductDto productDto) {
    params.put("productName", productDto.getProductName());
    params.put("category", productDto.getCategory().name());
    params.put("imageUrl", productDto.getImageUrl());
    params.put("price", productDto.getPrice());
    params.put("stock", productDto.getStock());
    params.put("description", productDto.getDescription());
    params.put("lastModifiedDate", now);
  }
}
