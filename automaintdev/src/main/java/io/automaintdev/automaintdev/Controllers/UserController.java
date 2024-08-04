package io.automaintdev.automaintdev.Controllers;

import io.automaintdev.automaintdev.Beans.SecUser;
import io.automaintdev.automaintdev.Beans.UserRole;
import io.automaintdev.automaintdev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new SecUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") SecUser user) {
        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        user.setEnabled(true);
        userRepository.saveUser(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(1L);

        userRepository.saveUserRole(userRole);

        return "redirect:/login";
    }
}
