package eu.senla;

import com.codeborne.selenide.WebDriverRunner;
import eu.senla.Endpoints.Endpoints;
import eu.senla.LoginPage.LoginPage;
import eu.senla.PropertyFile.ReadPropertyFile;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
  private final int priorityNumber = 3;
  @Epic("Authentication")
  @Feature("Login")
  @Story("User is able to login to the app with valid credentials")
  @Description("Verify that user is able to login with valid credentials")
  @Test(priority = 1, groups = "smoke", description = "Check Sign In with valid credentials")
  @Severity(SeverityLevel.CRITICAL)
  public void testValidLogin() {

    LoginPage loginPage = new LoginPage();
    loginPage.load().login(login, password).isLoginSuccessful();
    SoftAssert sa = new SoftAssert();
    sa.assertEquals(
        ReadPropertyFile.getProperty("BASEURL") + Endpoints.DASHBOARD_ENDPOINT,
        WebDriverRunner.url(),
        "Unsuccessful Login");
    sa.assertAll();
  }
  @Epic("Authentication")
  @Feature("Login")
  @Story("User is not able to login to the app with invalid credentials")
  @Description("Verify that user is not able to login with invalid credentials")
  @Severity(SeverityLevel.CRITICAL)
  @Test(
          description = "Check Sign In with invalid {0}",
          priority = 2,
          groups = "extended",
          dataProvider = "getCredentials",
          dataProviderClass = ProjectDataProvider.class)
  public void testInvalidLogin(String description, String username, String pwd) {
    LoginPage loginPage = new LoginPage();
    loginPage.load().login(username, pwd);
    SoftAssert sa = new SoftAssert();

    Allure.step(
            "Validate alert text",
            () -> sa.assertEquals("Invalid credentials", loginPage.getAlertText()));

    Allure.step(
            "Validate url",
            () ->
                    sa.assertEquals(
                            ReadPropertyFile.getProperty("BASEURL") + Endpoints.AUTH_ENDPOINT,
                            WebDriverRunner.url(),
                            "Url doesn't match"));
    sa.assertAll();
  }

  @Epic("Authentication")
  @Feature("Login")
  @Story("User is not able to login to the app with empty credentials")
  @Description("Verify that user is not able to login with empty credentials")
  @Severity(SeverityLevel.NORMAL)
  @Test(
          description = "Check Sign In with empty {0}",
          priority = priorityNumber,
          groups = "extended",
          dataProvider = "getEmptyCredentials",
          dataProviderClass = ProjectDataProvider.class)
  public void testEmptyLogin(String description, String username, String pwd) {
    LoginPage loginPage = new LoginPage();
    loginPage.load().login(username, pwd);
    SoftAssert sa = new SoftAssert();
    Allure.step(
            "Validate error alert text", () -> sa.assertEquals("Required", loginPage.getErrorText()));
    Allure.step(
            "Validate color",
            () ->
                    sa.assertEquals(
                            ReadPropertyFile.getProperty("COLOR"),
                            loginPage.getErrorColor(),
                            "Color value doesn't match"));
    Allure.step(
            "Validate url",
            () ->
                    sa.assertEquals(
                            ReadPropertyFile.getProperty("BASEURL") + Endpoints.AUTH_ENDPOINT,
                            WebDriverRunner.url(),
                            "Url doesn't match"));
    sa.assertAll();
  }
}
