package hr.algebra.bookify.backend.security.service;

import hr.algebra.bookify.backend.security.model.ApplicationUser;
import hr.algebra.bookify.backend.security.model.UserAuthentication;
import io.jsonwebtoken.*;
import hr.algebra.bookify.backend.security.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);
    private static final String AUTHORITY_KEY = "authority";

    @Value("${security.authentication.jwt.access-token-validity-seconds}")
    private Long accessTokenValiditySeconds;

    @Value("${security.authentication.jwt.base64-secret}")
    private String secretKey;

    public void authenticate(String token) {
        ApplicationUser applicationUser = getUserDataFromJwt(token);
        saveAuthentication(applicationUser);
    }

    public String createJwt(User user) {
        Instant expiration = Instant.now().plusSeconds(accessTokenValiditySeconds);
        String authority = user.getAuthority().toString();

        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setSubject(user.getUsername())
                .setExpiration(new Date(expiration.toEpochMilli()))
                .setIssuedAt(new Date())
                .claim(AUTHORITY_KEY, authority)
                .compact();
    }

    public boolean isJwtInvalid(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return false;
        } catch (SignatureException e) {
            log.debug("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.debug("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e.getMessage());
        }
        return true;
    }

    private ApplicationUser getUserDataFromJwt(String jwtToken) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(claims.get(AUTHORITY_KEY).toString());

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(claims.getSubject());
        applicationUser.setAuthority(authority);

        return applicationUser;
    }

    private void saveAuthentication(ApplicationUser applicationUser) {
        Authentication authentication = new UserAuthentication(applicationUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public String getAuthorityKey(String jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
        return claims.get(AUTHORITY_KEY).toString();
    }

}
