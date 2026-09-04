package Edupulse.Backend.auth.security;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final SecretKey secretKey;
    public JwtService(JwtProperties jwtProperties){
        this.secretKey = Keys.hmacShaKeyFor(
            jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }
}
