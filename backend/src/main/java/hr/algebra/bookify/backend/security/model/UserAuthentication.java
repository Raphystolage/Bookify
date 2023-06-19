package hr.algebra.bookify.backend.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.io.Serial;
import java.util.Collections;
import java.util.Objects;

public class UserAuthentication extends AbstractAuthenticationToken {

    private final ApplicationUser principal;

    public UserAuthentication(ApplicationUser principal) {
        super(Collections.singleton(principal.getAuthority()));
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "NO";
    }

    @Override
    public ApplicationUser getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserAuthentication that = (UserAuthentication) o;
        return Objects.equals(principal, that.principal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), principal);
    }
}
