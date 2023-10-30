package br.com.guitcamargo.springsecurityauthorizationwithroles.repositories;

import br.com.guitcamargo.springsecurityauthorizationwithroles.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserDetails> findByLogin(String login);
}