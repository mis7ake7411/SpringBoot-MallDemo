package com.mis7ake7411.springbootmall.dao.impl;


import com.mis7ake7411.springbootmall.dao.RoleDao;
import com.mis7ake7411.springbootmall.model.Role;
import com.mis7ake7411.springbootmall.rowmapper.RoleRowMapper;
import com.mis7ake7411.springbootmall.rowmapper.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    private RoleRowMapper roleRowMapper;

    @Override
    public Role getRoleByName(String roleName) {
        String sql = "SELECT role_id, role_name FROM role WHERE role_name = :roleName";

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", roleName);

        List<Role> roleList = namedParameterJdbcTemplate.query(sql, map, roleRowMapper);

        if (roleList.isEmpty()) {
            return null;
        } else {
            return roleList.get(0);
        }
    }
}
