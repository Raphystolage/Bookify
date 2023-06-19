package hr.algebra.bookify.backend.security.model;

import javax.persistence.*;

@Entity
@Table(name = "BookifyUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    public User() {}
    public User(Long id, String username, String password, Authority authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Authority getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

}
