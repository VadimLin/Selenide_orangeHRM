package eu.senla;

import com.codeborne.selenide.WebDriverRunner;
import eu.senla.Endpoints.Endpoints;
import eu.senla.LogoutPage.LogoutPage;
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

public class LogoutTest extends BaseTest {
  @Epic("Authentication")
  @Feature("Logout")
  @Story("User is  able to logout from the app")
  @Description("Verify that user is able to logout")
  @Severity(SeverityLevel.CRITICAL)
  @Test
  public void logoutTest() {
    LogoutPage logoutPage = new LogoutPage();
    logoutPage.openDropDownMenu().clickLogoutButton().getLoginTitle();
    SoftAssert sa = new SoftAssert();
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
