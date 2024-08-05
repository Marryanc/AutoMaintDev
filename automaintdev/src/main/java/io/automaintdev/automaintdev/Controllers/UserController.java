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

    // Mapping for the custom login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Returns the page for registration and initiates and new secUser Object
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new SecUser());
        return "register";
    }

    // Basic registration post mapping for the form
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") SecUser user) {
        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        user.setEnabled(true);
        userRepository.saveUser(user);

        // This sets the user role on signup
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(1L);

        userRepository.saveUserRole(userRole);

        return "redirect:/login";
    }

    // This is the route for when a user does not have permission
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "denied";
    }
}
