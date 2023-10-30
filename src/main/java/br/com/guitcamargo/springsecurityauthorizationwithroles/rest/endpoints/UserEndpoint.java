package br.com.guitcamargo.springsecurityauthorizationwithroles.rest.endpoints;

import br.com.guitcamargo.springsecurityauthorizationwithroles.domain.UserEntity;
import br.com.guitcamargo.springsecurityauthorizationwithroles.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {

    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
