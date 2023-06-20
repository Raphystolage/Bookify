package hr.algebra.bookify.webapp.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Base64;

public class JWT implements Serializable {

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
