package com.mis7ake7411.springbootmall.dao.impl;

import com.mis7ake7411.springbootmall.constant.ProductCategory;
import com.mis7ake7411.springbootmall.dao.ProductDao;
import com.mis7ake7411.springbootmall.dto.ProductDto;
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

    @Override
    public List<Product> getProducts(ProductCategory category, String search) {
        String sql = "SELECT product_id, product_name, category, image_url, " +
                     "price, stock, description, created_date, last_modified_date " +
                     "FROM product WHERE 1=1 ";
        Map<String, Object> params = new HashMap<String, Object>();
        if(category != null) {
            sql += "AND category = :category ";
            params.put("category", category.name());
        }
        if(search != null) {
            sql += "AND UPPER(product_name) LIKE :search ";
            params.put("search", "%" + search.toUpperCase() + "%");
        }
        List<Product> query = namedParameterJdbcTemplate.query(sql, params, new ProductRowMapper());
        return query;
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
        String sql = "INSERT INTO product(product_name, category, image_url, " +
                     "price, stock, description, created_date, last_modified_date) " +
                     "VALUES (:productName, :category, :imageUrl, :price, :stock," +
                     ":description, :createdDate, :lastModifiedDate)";
        Map<String, Object> params = new HashMap<>();
        params.put("productName", productDto.getProductName());
        params.put("category", productDto.getCategory().toString());
        params.put("imageUrl", productDto.getImageUrl());
        params.put("price", productDto.getPrice());
        params.put("stock", productDto.getStock());
        params.put("description", productDto.getDescription());
        Date now = new Date();
        params.put("createdDate", now);
        params.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey()
                        .intValue();
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {
        String sql = "UPDATE product SET product_name =:productName, category =:category, image_url =:imageUrl," +
                     "price =:price, stock =:stock, description =:description, last_modified_date =:lastModifiedDate " +
                     "WHERE product_id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("productName", productDto.getProductName());
        params.put("category", productDto.getCategory().toString());
        params.put("imageUrl", productDto.getImageUrl());
        params.put("price", productDto.getPrice());
        params.put("stock", productDto.getStock());
        params.put("description", productDto.getDescription());
        params.put("lastModifiedDate", new Date());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteProductById(Integer id) {
        String sql = "DELETE FROM product WHERE product_id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterJdbcTemplate.update(sql, params);
    }

}
