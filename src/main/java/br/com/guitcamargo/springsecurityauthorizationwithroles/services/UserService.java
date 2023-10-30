package br.com.guitcamargo.springsecurityauthorizationwithroles.services;

import br.com.guitcamargo.springsecurityauthorizationwithroles.domain.UserEntity;
import br.com.guitcamargo.springsecurityauthorizationwithroles.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public List<UserEntity> findAll(){
        return this.repository.findAll();
    }

    public Optional<UserDetails> findByLogin(String login){
        return this.repository.findByLogin(login);
    }
}
