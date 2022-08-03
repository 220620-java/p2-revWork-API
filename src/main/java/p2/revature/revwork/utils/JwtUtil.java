package p2.revature.revwork.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;


@Component
public class JwtUtil {

	private static JWTVerifier jwtVerifier;
	
    private JwtUtil() {
    }

    static {
    	jwtVerifier = null;
    }
	
   public static String pullFromHeader(String headerString) {
    	String arr = headerString.split("\\.")[1];

    	new String(Base64.decodeBase64(arr));
    	return arr;
    }
	
    @Bean
    @Scope("prototype")
    public JWTCreator.Builder getJwtBuilder() {
        return JWT.create();
    }
	
    @Bean
    @Scope("singleton")
    public JWTVerifier getJwtVerifierr() {
    	if ( jwtVerifier == null ) {
    		Algorithm algorithm = Algorithm.HMAC256("michael"); //use more secure key
    		
    		jwtVerifier = JWT.require(algorithm)
			        .withIssuer("auth0")
			        .build(); //Reusable verifier instance
    	}
    	
    	return jwtVerifier;
    }
    
}
