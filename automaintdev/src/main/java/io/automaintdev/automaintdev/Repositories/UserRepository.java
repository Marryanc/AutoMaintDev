package io.automaintdev.automaintdev.Repositories;

import io.automaintdev.automaintdev.Beans.SecRole;
import io.automaintdev.automaintdev.Beans.SecUser;
import io.automaintdev.automaintdev.Beans.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Method to find a user account by email
    public SecUser findUserAccount(String email) {
        String query = "SELECT * FROM sec_user WHERE email = :email";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);

        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(SecUser.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    // Method to get User Roles for a specific User id
    public List<String> getRolesById(Long userId) {
        String query = "SELECT sec_role.role_name " +
                       "FROM user_role, sec_role " +
                       "WHERE user_role.role_id = sec_role.role_id " +
                       "AND user_role.user_id = :userId";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", userId);

        return jdbc.queryForList(query, namedParameters, String.class);
    }

    // Method to save a new user
    public void saveUser(SecUser user) {
        String query = "INSERT INTO sec_user (email, password, enabled) VALUES (:email, :password, :enabled)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("password", encoder.encode(user.getEncryptedPassword()));
        namedParameters.addValue("enabled", user.getEnabled());

        jdbc.update(query, namedParameters);

        // Retrieve the generated userId
        Long userId = jdbc.queryForObject("SELECT user_id FROM sec_user WHERE email = :email", namedParameters, Long.class);
        user.setUserId(userId);
    }

    // Method to save a new user role
    public void saveUserRole(UserRole userRole) {
        String query = "INSERT INTO user_role (user_id, role_id) VALUES (:userId, :roleId)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("userId", userRole.getUserId());
        namedParameters.addValue("roleId", userRole.getRoleId());

        jdbc.update(query, namedParameters);
    }

    // Method to find a role by name
    public SecRole findByRoleName(String roleName) {
        String query = "SELECT * FROM sec_role WHERE role_name = :roleName";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("roleName", roleName);

        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(SecRole.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
}
