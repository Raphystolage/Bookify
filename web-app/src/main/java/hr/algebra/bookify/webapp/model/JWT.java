package hr.algebra.bookify.webapp.model;

public class JWT {

    private String jwt;

    public JWT() {}
    public JWT(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return jwt;
    }
}
