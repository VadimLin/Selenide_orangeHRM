package eu.senla.Client;

import static io.restassured.RestAssured.given;

import eu.senla.Endpoints.Endpoints;
import eu.senla.PropertyFile.ReadPropertyFile;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@UtilityClass
public final class LoginHelper {

  private String[] getInitialCookie() {
    String[] arrayOfValues = new String[2];
    String token = "";

    Response firstResponse =
        OrangeHRMClient.getRequest(
            Endpoints.MAIN_URL + Endpoints.WEB_EP + Endpoints.AUTH_LOGIN_URL);

    String htmlContent = firstResponse.asString();
    Document doc = Jsoup.parse(htmlContent);

    Element authLogin = doc.selectFirst("auth-login");
    if (authLogin != null) {
      token = authLogin.attr(":token");
      token = token.substring(1, token.length() - 1);
      System.out.println("Token value: " + token);
    } else {
      System.out.println("<auth-login> element not found");
    }

    arrayOfValues[0] = firstResponse.getDetailedCookie("orangehrm").getValue();
    arrayOfValues[1] = token;

    return arrayOfValues;
  }

  public String getCookie() {

    final int responseCode = 302;
    final String[] arrayOfValues = getInitialCookie();

    RequestSpecification requestPostSpecification =
        given()
            .cookie("orangehrm", arrayOfValues[0])
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("_token", arrayOfValues[1])
            .formParam("username", ReadPropertyFile.getProperty("USERNAME"))
            .formParam("password", ReadPropertyFile.getProperty("PASSWORD"))
            .log()
            .all();

    ValidatableResponse secondResponse =
        OrangeHRMClient.postValidateRequest(
            requestPostSpecification,
            Endpoints.MAIN_URL + Endpoints.WEB_EP + Endpoints.AUTH_VALIDATE_URL);
    secondResponse.statusCode(responseCode);

    Cookie validateCookie = secondResponse.extract().detailedCookie("orangehrm");

    return validateCookie.getValue();
  }
}
