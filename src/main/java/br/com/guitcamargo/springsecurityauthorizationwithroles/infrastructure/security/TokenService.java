package br.com.guitcamargo.springsecurityauthorizationwithroles.infrastructure.security;

import br.com.guitcamargo.springsecurityauthorizationwithroles.domain.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String SECRET = "#0123456789";

    public String generateToken(UserEntity user){
        try {
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationDateJwt())
                    .sign(getSign());
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    public String validateToken(String token){
        try {
            return JWT.require(getSign())
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Erro ao validar token", e);
        }
    }

    private static Algorithm getSign() {
        return Algorithm.HMAC256(SECRET);
    }

    private Instant getExpirationDateJwt(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }


}
