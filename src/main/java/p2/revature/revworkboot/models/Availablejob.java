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
 * A job the employer is trying to find a freelancer to complete.
 */
@ApiModel(description = "A job the employer is trying to find a freelancer to complete.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-07-29T09:44:07.392-05:00[America/Chicago]")
public class Availablejob   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("skills")
  private String skills = null;

  @JsonProperty("jobdescription")
  private String jobdescription = null;

  @JsonProperty("payrate")
  private String payrate = null;

  public Availablejob title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Availablejob skills(String skills) {
    this.skills = skills;
    return this;
  }

  /**
   * Get skills
   * @return skills
  **/
  @ApiModelProperty(value = "")
  
    public String getSkills() {
    return skills;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public Availablejob jobdescription(String jobdescription) {
    this.jobdescription = jobdescription;
    return this;
  }

  /**
   * Get jobdescription
   * @return jobdescription
  **/
  @ApiModelProperty(value = "")
  
    public String getJobdescription() {
    return jobdescription;
  }

  public void setJobdescription(String jobdescription) {
    this.jobdescription = jobdescription;
  }

  public Availablejob payrate(String payrate) {
    this.payrate = payrate;
    return this;
  }

  /**
   * Get payrate
   * @return payrate
  **/
  @ApiModelProperty(value = "")
  
    public String getPayrate() {
    return payrate;
  }

  public void setPayrate(String payrate) {
    this.payrate = payrate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Availablejob availablejob = (Availablejob) o;
    return Objects.equals(this.title, availablejob.title) &&
        Objects.equals(this.skills, availablejob.skills) &&
        Objects.equals(this.jobdescription, availablejob.jobdescription) &&
        Objects.equals(this.payrate, availablejob.payrate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, skills, jobdescription, payrate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Availablejob {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    skills: ").append(toIndentedString(skills)).append("\n");
    sb.append("    jobdescription: ").append(toIndentedString(jobdescription)).append("\n");
    sb.append("    payrate: ").append(toIndentedString(payrate)).append("\n");
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
