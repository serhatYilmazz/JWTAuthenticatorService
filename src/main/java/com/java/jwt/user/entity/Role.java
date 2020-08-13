package com.java.jwt.user.entity;

import com.java.jwt.common.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class Role extends AbstractEntity {

    @Column(name = "ROLE_PERMISSON")
    @Enumerated(EnumType.STRING)
    private RolePermission rolePermission;
}
