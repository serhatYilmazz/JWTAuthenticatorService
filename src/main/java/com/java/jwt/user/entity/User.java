package com.java.jwt.user.entity;

import com.java.jwt.common.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "username", callSuper = false)
@Entity
@Table(name = "PERSON")
public class User extends AbstractEntity {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String eMail;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roleSet;

}
