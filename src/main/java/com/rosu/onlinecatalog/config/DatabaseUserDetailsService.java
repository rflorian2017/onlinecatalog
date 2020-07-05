package com.rosu.onlinecatalog.config;

import com.rosu.onlinecatalog.config.CustomUserDetails;
import com.rosu.onlinecatalog.model.PendingUser;
import com.rosu.onlinecatalog.model.User;
import com.rosu.onlinecatalog.repository.PendingUserRepository;
import com.rosu.onlinecatalog.repository.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Getter
@Slf4j
public class DatabaseUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private PendingUserRepository pendingUserRepository;

    @Autowired
    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Username " + username);
        Optional<PendingUser> optional = pendingUserRepository.findByUsername(username);

        if(optional.isPresent()) {
            log.info(optional.get().getActivationCode());
            throw new UsernameNotFoundException(username);
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
        return new CustomUserDetails(user);
    }
}
