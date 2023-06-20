package hr.algebra.bookify.webapp.model;

import org.json.JSONObject;

import java.util.Base64;

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

    public String getAuthority() {
        try {
            String string = jwt.split("\\.")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(string);
            String decodedString = new String(decodedBytes, "UTF-8");
            JSONObject jsonObject = new JSONObject(decodedString);
            return jsonObject.getString("authority");
        } catch (Exception e) {
            System.out.println("Error while getting authority" + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println((new JWT("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjg3ODAyOTMxLCJpYXQiOjE2ODcxOTgxMzEsImF1dGhvcml0eSI6IlVTRVIifQ.IZxUJYLGf6_OqNaRWZ4c4E9v0TZ6u1wlGeZ9Qn3nCaNptoFACjP7Kstca1BljP4WSLnCfqs1IdDuTwBtFWBqFw")).getAuthority());
    }
}
