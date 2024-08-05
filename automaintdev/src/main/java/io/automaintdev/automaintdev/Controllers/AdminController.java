package io.automaintdev.automaintdev.Controllers;

import io.automaintdev.automaintdev.Beans.SecRole;
import io.automaintdev.automaintdev.Beans.SecUser;
import io.automaintdev.automaintdev.Beans.UserRole;
import io.automaintdev.automaintdev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<SecUser> users = userRepository.findAllUsers();
        List<SecRole> roles = userRepository.findAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "admin";
    }

    @PostMapping("/admin/assignRole")
    public String assignRole(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        UserRole userRole = new UserRole(userId, roleId);
        userRepository.saveUserRole(userRole);
        return "redirect:/admin";
    }
}
