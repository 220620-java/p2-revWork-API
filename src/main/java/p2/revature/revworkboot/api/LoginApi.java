/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package p2.revature.revworkboot.api;

import p2.revature.revworkboot.models.Usernameandpassword;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-07-28T18:48:17.110-05:00[America/Chicago]")
@Api(value = "login", description = "the login API")
public interface LoginApi {

    @ApiOperation(value = "", nickname = "loginPost", notes = "", authorizations = {
        @Authorization(value = "revwork_security_schema")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The login was a sucess.") })
    @RequestMapping(value = "/login",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> loginPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Usernameandpassword body
);

}
