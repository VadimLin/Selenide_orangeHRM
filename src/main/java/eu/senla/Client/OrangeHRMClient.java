package eu.senla.Client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class OrangeHRMClient {

  public Response getRequest(String url) {
    return given().when().get(url);
  }

  public ValidatableResponse postValidateRequest(RequestSpecification reqSpec, String url) {
    return reqSpec.when().post(url).then().log().all();
  }
}
