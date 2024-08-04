package io.automaintdev.automaintdev.Services;

import io.automaintdev.automaintdev.Beans.SecUser;
import io.automaintdev.automaintdev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SecUser secUser = userRepository.findUserAccount(email);
        if (secUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = userRepository.getRolesById(secUser.getUserId()).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(secUser.getEmail(), secUser.getEncryptedPassword(), authorities);
    }
}
