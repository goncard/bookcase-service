package service;

import dto.AuthRequest;
import infrastructure.database.entity.User;
import infrastructure.database.repository.UserRepository;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import util.TokenUtils;
import io.quarkus.elytron.security.common.BcryptUtil;

@ApplicationScoped
public class AuthService {

    private final UserRepository userRepository;

    @Inject
    public AuthService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(final AuthRequest authRequest) {
        final User user = userRepository.findBy(authRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("There is no user with this email address"));

        if (!BcryptUtil.matches(authRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Wrong credentials");
        }

        return TokenUtils.generateToken(user.getEmail());
    }
}
