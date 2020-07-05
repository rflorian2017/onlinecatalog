package com.rosu.onlinecatalog.controller;

import com.rosu.onlinecatalog.model.PendingUser;
import com.rosu.onlinecatalog.model.User;
import com.rosu.onlinecatalog.repository.PendingUserRepository;
import com.rosu.onlinecatalog.repository.UserRepository;
import com.rosu.onlinecatalog.service.RandomStringGenerator;
import com.rosu.onlinecatalog.service.SendGridEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegisterController {

    // TODO: create user service

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PendingUserRepository pendingUserRepository;

    @Autowired
    private RandomStringGenerator randomStringGenerator;

    @Autowired
    private SendGridEmailService sendGridEmailService;

    @GetMapping("/register")
    public String registerUser() {
        return "security/register";
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String emailAddress) {
        User user = new User();
        user.setEmailAddress(emailAddress);
        user.setPassword(encoder().encode(password));
        user.setUsername(username);

        userRepository.save(user);
        PendingUser pendingUser = new PendingUser();
        String activationCode = randomStringGenerator.getAlphaNumericString(20);
        pendingUser.setActivationCode(activationCode);
        sendGridEmailService.sendHTML("buhaidebalta.15@gmail.com",
                user.getEmailAddress(), "Please confirm account",
                randomStringGenerator.linkCreator(activationCode,
                        "https://online-school-catalog-rs.herokuapp.com"));
        pendingUser.setUser(user);
        pendingUserRepository.save(pendingUser);

        return "redirect:/login";
    }

    @GetMapping("/userValidation")
    public String validateUser(String activationCode) {
        Optional<PendingUser> optional = pendingUserRepository.findByActivationCode(activationCode);
        if(optional.isPresent()) {
            PendingUser pendingUser = optional.get();

            pendingUserRepository.delete(pendingUser);
        }
        return "security/login";

    }
}
