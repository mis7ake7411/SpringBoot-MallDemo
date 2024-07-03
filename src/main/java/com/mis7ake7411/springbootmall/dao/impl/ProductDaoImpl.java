package com.mis7ake7411.springbootmall.dao.impl;

import com.mis7ake7411.springbootmall.dao.ProductDao;
import com.mis7ake7411.springbootmall.model.Product;
import com.mis7ake7411.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
}
