package service;

import dto.SignUpRequest;
import infrastructure.database.entity.User;
import infrastructure.database.repository.UserRepository;
import infrastructure.exception.UserAlreadyExistsException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapper.UserMapper;

import java.util.Optional;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Inject
    public UserService(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void signUp(final SignUpRequest signUpRequest) throws UserAlreadyExistsException {
        final Optional<User> user = userRepository.findBy(signUpRequest.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("A user with this email already exists");
        }

        userRepository.persist(userMapper.toEntity(signUpRequest));
    }
}
