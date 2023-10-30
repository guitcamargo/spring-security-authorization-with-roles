package br.com.guitcamargo.springsecurityauthorizationwithroles.infrastructure.security;

import br.com.guitcamargo.springsecurityauthorizationwithroles.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String WITHOUT_HEADER = "WITHOUT_HEADER";
    private static final String BEARER_AUTH = "Bearer ";

    private final TokenService tokenService;

    private final UserService userService;

    public AuthorizationFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoveryToken(request);

        if (!token.equals(WITHOUT_HEADER)) {
            var login = tokenService.validateToken(token);
            Optional<UserDetails> user = userService.findByLogin(login);

            if (user.isPresent()){
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }


    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authHeader == null) return WITHOUT_HEADER;
        return authHeader.replace(BEARER_AUTH, "");
    }
}
