package app.user.service;

import app.exception.DomainException;
import app.user.model.User;
import app.user.repository.UserRepository;
import app.web.dto.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegisterRequest registerRequest) {

        Optional<User> userOptional = userRepository.findByUsername(registerRequest.getUsername());
        if (userOptional.isPresent()) {
            throw new DomainException("Username [%s] is already in use".formatted(registerRequest.getUsername()));
        }

        User user = initializeUser(registerRequest);

        return null;
    }

    private User initializeUser(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
    }
}
