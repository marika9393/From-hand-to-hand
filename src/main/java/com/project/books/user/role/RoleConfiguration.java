package com.project.books.user.role;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@Component
@Validated
public class RoleConfiguration {
    private Set<String> roles;
    private String defaultRole;

    public boolean isDefaultRoleShouldBeInRoles(){
        if(defaultRole == null){
            return false;
        }
        return roles.contains(defaultRole);
    }
}
