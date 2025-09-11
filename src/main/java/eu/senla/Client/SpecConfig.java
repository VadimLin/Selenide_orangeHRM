package eu.senla.Client;

import eu.senla.PropertyFile.ReadPropertyFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecConfig {
  private final int statusCode = 200;

  public static RequestSpecification requestSpecification() {
    return new RequestSpecBuilder()
        .setBaseUri(ReadPropertyFile.getProperty("MAIN_URL"))
        .addCookie("orangehrm", LoginHelper.getCookie())
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
  }

  public static ResponseSpecification responseSpecification() {
    return new ResponseSpecBuilder().log(LogDetail.ALL).expectStatusCode(statusCode).build();
  }
}
