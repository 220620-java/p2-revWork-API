package p2.revature.revwork.utils;

import java.io.UnsupportedEncodingException;

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
	
   public static String pullFromHeader(String headerString) throws UnsupportedEncodingException {
    	String[] arr = headerString.split("\\.");
    	String payload = arr[1];
    	String jsonString = new String(Base64.decodeBase64(payload), "UTF-8");
    	return jsonString;
    }
   
   public static int getId(String token) {
	   
	   Integer id = null;
	   
		try {
			String[] split = JwtUtil.pullFromHeader(token).split(",");
			String stringId = split[3].substring(5);
			 id = Integer.parseInt(stringId); 

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
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
