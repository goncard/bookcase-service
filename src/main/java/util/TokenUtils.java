package util;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenUtils {
    public static String generateToken(final String email) {
        return Jwt.issuer("bookcase-server")
                .upn(email)
                .expiresAt(System.currentTimeMillis()+3600)
                .sign();
    }
}
