package com.nguyenanhthu.ptm.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nguyenanhthu.ptm.user.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private Integer departmentId;

    @JsonIgnore
    private String password;


    public UserDetailsImpl(Long id, String username, Integer departmentId, String password) {
        this.id = id;
        this.username = username;
        this.departmentId = departmentId;
        this.password = password;
    }

    public static UserDetailsImpl build(UserModel user) {

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getDepartmentId(),
                user.getPassword());
    }


    public Long getId() {
        return id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}