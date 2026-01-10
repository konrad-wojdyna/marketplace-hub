package com.marketplace.backend.user.service;

import com.marketplace.backend.user.dto.UserRegisterRequest;
import com.marketplace.backend.user.dto.UserResponse;
import com.marketplace.backend.user.entity.User;
import com.marketplace.backend.user.exception.UserAlreadyExistsException;
import com.marketplace.backend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterNewUser() {
        UserRegisterRequest request =
                new UserRegisterRequest(
                        "test@test.com",
                        "Password123!",
                        "John",
                        "Doe");

        User user = User.builder()
                .id(1L)
                .email(request.email())
                .passwordHash("hashedPassword")
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        //When
        UserResponse response = userService.register(request);

        //Then
        assertNotNull(response);
        assertThat(response.email()).isEqualTo(request.email());
        assertThat(response.firstName()).isEqualTo(request.firstName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        UserRegisterRequest request =
                new UserRegisterRequest(
                        "exists@test.com",
                        "Password123!",
                        "Jane",
                        "Doe");

        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.register(request));
        verify(userRepository, never()).save(any());
    }


    @Test
    void shouldHashPasswordWithBcrypt() {
        UserRegisterRequest request =
                new UserRegisterRequest(
                        "test@test.com",
                        "PlainPassword123!",
                        "John",
                        "Doe"
                );

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenReturn("hashedPassword123");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //When
        userService.register(request);

        //Then
        verify(passwordEncoder, times(1)).encode(request.password());
    }
}