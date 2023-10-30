package br.com.guitcamargo.springsecurityauthorizationwithroles.rest.endpoints;

import br.com.guitcamargo.springsecurityauthorizationwithroles.infrastructure.security.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationEndpoint {

    private final AuthorizationService service;

    public AuthorizationEndpoint(AuthorizationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok(service.getTokenByLogin());
    }

}