/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package p2.revature.revworkboot.api;

import p2.revature.revworkboot.models.Application;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-07-28T17:00:54.280-05:00[America/Chicago]")
@Api(value = "apply", description = "the apply API")
public interface ApplyApi {

    @ApiOperation(value = "Apply for an available job", nickname = "applyPost", notes = "", authorizations = {
        @Authorization(value = "revwork_security_schema")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Application has been sent!") })
    @RequestMapping(value = "/apply",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> applyPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Application body
);


    @ApiOperation(value = "Update an application.", nickname = "applyPut", notes = "", authorizations = {
        @Authorization(value = "revwork_security_schema")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Application has been updated.") })
    @RequestMapping(value = "/apply",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> applyPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Application body
);

}
