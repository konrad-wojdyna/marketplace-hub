package com.marketplace.backend.user.service;


import com.marketplace.backend.user.dto.UserRegisterRequest;
import com.marketplace.backend.user.dto.UserResponse;
import com.marketplace.backend.user.entity.User;
import com.marketplace.backend.user.exception.UserAlreadyExistsException;
import com.marketplace.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRegisterRequest request) {
        log.info("Registering user with email: {}", request.email());

        if(userRepository.existsByEmail(request.email())) {
            log.warn("User with email {} already exists", request.email());
            throw new UserAlreadyExistsException("User with email " + request.email() + " already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .email(request.email())
                .passwordHash(hashedPassword)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User with email {} registered successfully with id {}", savedUser.getEmail(), savedUser.getId());

        return mapToUserResponse(savedUser);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getAvatarUrl(),
                user.getCityId(),
                user.getBio(),
                user.getIsVerified(),
                user.getIsEmailVerified(),
                user.getIsPhoneVerified(),
                user.getLastActiveAt(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }


}
