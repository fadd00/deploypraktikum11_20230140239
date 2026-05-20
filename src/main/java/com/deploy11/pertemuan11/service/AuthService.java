package com.deploy11.pertemuan11.service;

import com.deploy11.pertemuan11.model.Profile;
import com.deploy11.pertemuan11.model.User;
import com.deploy11.pertemuan11.model.dto.RegisterRequest;
import com.deploy11.pertemuan11.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest req) {
        Optional<User> existing = userRepository.findByUsername(req.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setNama(req.getNama());

        Profile profile = new Profile();
        profile.setAlamat(req.getAlamat());
        profile.setUser(user);
        user.setProfile(profile);

        return userRepository.save(user);
    }

    public Optional<User> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails ud) {
            return userRepository.findByUsername(ud.getUsername());
        }
        return Optional.empty();
    }
}
