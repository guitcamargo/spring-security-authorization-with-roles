package br.com.guitcamargo.springsecurityauthorizationwithroles.infrastructure.security;

import br.com.guitcamargo.springsecurityauthorizationwithroles.domain.UserEntity;
import br.com.guitcamargo.springsecurityauthorizationwithroles.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final ApplicationContext context;

    private final UserService userService;

    private final TokenService tokenService;

    private AuthenticationManager authenticationManager;

    public AuthorizationService(ApplicationContext context, UserService userService, TokenService tokenService) {
        this.context = context;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.userService.findByLogin(login).get();
    }

    public String getTokenByLogin(){
        authenticationManager =  this.context.getBean(AuthenticationManager.class);
        var usernamePassword = new UsernamePasswordAuthenticationToken("login", "senha");
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((UserEntity) auth.getPrincipal());
    }

    public UserEntity registerUser(){
       if(this.userService.findByLogin("").isPresent()){
           return null;
       }
        String encryptedPassword = new BCryptPasswordEncoder().encode("senha");
//       userService.create(user)
        return null;
    }
}