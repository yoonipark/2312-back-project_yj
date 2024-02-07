package com.github.backproject.respository.userPrincipal;

import com.github.backproject.respository.user.UserEntity;
import com.github.backproject.respository.userPrincipalRoles.UserPrincipalRolesEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_principal")
public class UserPrincipalEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_principal_id", nullable = false)
    private Integer userPrincipalId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userPrincipalEntity")
    private List<UserPrincipalRolesEntity> userPrincipalRoles;

}
