package com.hpi.tpc.data.entities;

import java.util.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

public class CustomUser extends User {
    private static final long serialVersionUID = -3531439484732724601L;
    private Integer id;
    private String name;
    private String email;
    
    public CustomUser(String username, 
        String password, boolean enabled, 
        boolean accountNonExpired, boolean credentialsNonExpired, 
        boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities) {
        super(username, password,
            enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked,
            authorities);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
