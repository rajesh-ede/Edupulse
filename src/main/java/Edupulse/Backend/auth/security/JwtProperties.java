package Edupulse.Backend.auth.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.annotation.PostConstruct;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    private long expiration;

    public String getSecret(){
        return secret;
    }
    public void setSecret(String secret){
        this.secret = secret;
    }
    public long getExpiration(){
        return expiration;
    }
    public void setExpiration(long expiration){
        this.expiration = expiration;
    }

    @PostConstruct
    public void checkProperties() {
        System.out.println("JWT secret loaded: " + (secret != null));
        System.out.println("JWT expiration loaded: " + expiration);
    }
}
