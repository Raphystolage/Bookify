package hr.algebra.bookify.backend.security.model;

public class JWT {

    private final String jwt;

    public JWT(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
