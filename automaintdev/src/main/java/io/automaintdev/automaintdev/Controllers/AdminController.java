package io.automaintdev.automaintdev.Controllers;

import io.automaintdev.automaintdev.Beans.UserRole;
import io.automaintdev.automaintdev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("users", userRepository.findAllUsers());
        model.addAttribute("allRoles", userRepository.findAllRoles());
        return "admin";
    }

    @PostMapping("/addRole")
    public String addRole(@RequestParam Long userId, @RequestParam Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRepository.saveUserRole(userRole);
        return "redirect:/admin";
    }

    @PostMapping("/removeRole")
    public String removeRole(@RequestParam Long userId, @RequestParam Long roleId) {
        userRepository.removeUserRole(userId, roleId);
        return "redirect:/admin";
    }
}
