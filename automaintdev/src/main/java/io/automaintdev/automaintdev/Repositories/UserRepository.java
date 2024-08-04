package io.automaintdev.automaintdev.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import io.automaintdev.automaintdev.Beans.SecUser;

@Repository
public class UserRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    // Method to find a user account by email
    public SecUser findUserAccount(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user WHERE email = :email";
        namedParameters.addValue("email", email);
        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(SecUser.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    // Method to get User Roles for a specific User id
    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT sec_role.roleName " +
                       "FROM user_role, sec_role " +
                       "WHERE user_role.roleId = sec_role.roleId " +
                       "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters, String.class);
    }
}
