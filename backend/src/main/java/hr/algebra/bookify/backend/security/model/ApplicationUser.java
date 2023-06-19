package hr.algebra.bookify.backend.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ApplicationUser {

    private String username;
    private String password;
    private SimpleGrantedAuthority authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SimpleGrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(SimpleGrantedAuthority authority) {
        this.authority = authority;
    }
}
