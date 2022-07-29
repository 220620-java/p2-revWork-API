package p2.revature.revworkboot.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Application
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-07-29T09:44:07.392-05:00[America/Chicago]")
public class Application   {
  @JsonProperty("jobid")
  private String jobid = null;

  @JsonProperty("portfolioid")
  private String portfolioid = null;

  @JsonProperty("coverletter")
  private String coverletter = null;

  public Application jobid(String jobid) {
    this.jobid = jobid;
    return this;
  }

  /**
   * Get jobid
   * @return jobid
  **/
  @ApiModelProperty(value = "")
  
    public String getJobid() {
    return jobid;
  }

  public void setJobid(String jobid) {
    this.jobid = jobid;
  }

  public Application portfolioid(String portfolioid) {
    this.portfolioid = portfolioid;
    return this;
  }

  /**
   * Get portfolioid
   * @return portfolioid
  **/
  @ApiModelProperty(value = "")
  
    public String getPortfolioid() {
    return portfolioid;
  }

  public void setPortfolioid(String portfolioid) {
    this.portfolioid = portfolioid;
  }

  public Application coverletter(String coverletter) {
    this.coverletter = coverletter;
    return this;
  }

  /**
   * Get coverletter
   * @return coverletter
  **/
  @ApiModelProperty(value = "")
  
    public String getCoverletter() {
    return coverletter;
  }

  public void setCoverletter(String coverletter) {
    this.coverletter = coverletter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application application = (Application) o;
    return Objects.equals(this.jobid, application.jobid) &&
        Objects.equals(this.portfolioid, application.portfolioid) &&
        Objects.equals(this.coverletter, application.coverletter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobid, portfolioid, coverletter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Application {\n");
    
    sb.append("    jobid: ").append(toIndentedString(jobid)).append("\n");
    sb.append("    portfolioid: ").append(toIndentedString(portfolioid)).append("\n");
    sb.append("    coverletter: ").append(toIndentedString(coverletter)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
