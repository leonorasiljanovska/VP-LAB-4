package mk.finki.ukim.mk.lab1.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR;


    @Override
    public String getAuthority() {
        return name();
    }
}
