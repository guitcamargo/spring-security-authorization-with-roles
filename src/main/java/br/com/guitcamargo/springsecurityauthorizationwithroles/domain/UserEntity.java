package br.com.guitcamargo.springsecurityauthorizationwithroles.domain;

import br.com.guitcamargo.springsecurityauthorizationwithroles.domain.enumerator.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_LOGIN")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

}
