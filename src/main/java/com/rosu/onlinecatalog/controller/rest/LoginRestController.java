package com.rosu.onlinecatalog.controller.rest;

import com.rosu.onlinecatalog.config.DatabaseUserDetailsService;
import com.rosu.onlinecatalog.config.JwtProvider;
import com.rosu.onlinecatalog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private DatabaseUserDetailsService databaseUserDetailsService;

    @Autowired
    public LoginRestController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, DatabaseUserDetailsService databaseUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.databaseUserDetailsService = databaseUserDetailsService;
    }

    @PostMapping
    public ResponseEntity createToken(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = databaseUserDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtProvider.createToken(userDetails.getUsername());

        return ResponseEntity.ok(token);
    }
}
