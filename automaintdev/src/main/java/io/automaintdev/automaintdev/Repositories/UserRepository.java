package io.automaintdev.automaintdev.Repositories;

import io.automaintdev.automaintdev.Beans.SecRole;
import io.automaintdev.automaintdev.Beans.SecUser;
import io.automaintdev.automaintdev.Beans.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public SecUser findUserAccount(String email) {
        String query = "SELECT * FROM sec_user WHERE email = :email";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("email", email);

        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(SecUser.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    public List<String> getRolesById(Long userId) {
        String query = "SELECT sec_role.roleName " +
                       "FROM user_role, sec_role " +
                       "WHERE user_role.roleId = sec_role.roleId " +
                       "AND user_role.userId = :userId";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("userId", userId);

        return jdbc.queryForList(query, namedParameters, String.class);
    }

    public void saveUser(SecUser user) {
        String query = "INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES (:email, :password, :enabled)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("password", user.getEncryptedPassword()); // Pass encoded password
        namedParameters.addValue("enabled", user.getEnabled());

        jdbc.update(query, namedParameters);

        // Retrieve the generated userId
        Long userId = jdbc.queryForObject("SELECT userId FROM sec_user WHERE email = :email", namedParameters, Long.class);
        user.setUserId(userId);
    }

    public void saveUserRole(UserRole userRole) {
        String query = "INSERT INTO user_role (userId, roleId) VALUES (:userId, :roleId)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", userRole.getUserId());
        namedParameters.addValue("roleId", userRole.getRoleId());

        jdbc.update(query, namedParameters);
    }

    public SecRole findByRoleName(String roleName) {
        String query = "SELECT * FROM sec_role WHERE roleName = :roleName";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("roleName", roleName);

        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(SecRole.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
}
