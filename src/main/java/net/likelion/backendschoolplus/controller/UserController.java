package net.likelion.backendschoolplus.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.likelion.backendschoolplus.entity.User;
import net.likelion.backendschoolplus.entity.UserRole;
import net.likelion.backendschoolplus.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String rePassword, String email) {
        log.info("username: " + username + " password: " + password + " rePassword: " + rePassword + " email: " + email);
        if (userService.findByUsername(username) == null && password.equals(rePassword) && password.length() >= 4) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = User.builder()
                    .username(username)
                    .password(hashedPassword)
                    .email(email)
                    .registrationDate(LocalDate.now())
                    .role(UserRole.USER)
                    .build();
            userService.registerUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/update/{userId}")
    public String updateForm(@PathVariable(name = "userId") Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(String username, String password, String rePassword, String email, UserRole role) {
        User user = userService.findByUsername(username);
        if (password.equals(rePassword) && password.length() >= 4) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        userService.updateUser(user);
        return "redirect:/users";
    }
}
