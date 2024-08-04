package io.automaintdev.automaintdev.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.automaintdev.automaintdev.Beans.SecUser;
import io.automaintdev.automaintdev.Repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UserRepository da;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: " + username);
        
        SecUser user = da.findUserAccount(username);
        if (user == null) {
            logger.severe("User not found: " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        
        List<String> roleNameList = da.getRolesById(user.getUserId());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNameList != null) {
            for (String role : roleNameList) {
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }

        logger.info("User found: " + username + ", with roles: " + roleNameList);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPassword(), user.getEnabled(), true, true, true, grantList);
    }
}
