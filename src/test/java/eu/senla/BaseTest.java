package eu.senla;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import eu.senla.LoginPage.LoginPage;
import eu.senla.LogoutPage.LogoutPage;
import eu.senla.PropertyFile.ReadPropertyFile;
import groovy.util.logging.Slf4j;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
@Slf4j
public class BaseTest {
  protected String login = ReadPropertyFile.getProperty("USERNAME");
  protected String password = ReadPropertyFile.getProperty("PASSWORD");

  @BeforeMethod
  public void setup() {

    Configuration.browser = ReadPropertyFile.getProperty("BROWSER");
    Configuration.timeout = Long.parseLong(ReadPropertyFile.getProperty("TIMEOUT"));
    Configuration.browserSize = ReadPropertyFile.getProperty("BROWSER.SIZE");
    Configuration.reportsFolder = "target/allure-results";
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//    Configuration.remote = ReadPropertyFile.getProperty("selenoidUIUrl");
//    Configuration.headless = false;


  }
//    @BeforeMethod
//    final void apiLogin() {
//      init();
//
//      ApiLogin authenticate =
//          new ApiLogin(Endpoints.MAIN_URL + Endpoints.WEB_EP + Endpoints.DASHBOARD_URL);
//
//      authenticate.login();
//    }
//
//    final void init() {
//      open("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//
//    }

  @AfterMethod
  public void tearDown() {
    Selenide.closeWebDriver();
  }

  public void loginAsUser() {
    LoginPage loginPage = new LoginPage();
    loginPage.load().login(login, password);
  }

      public void logoutUser() {
          new LogoutPage().openDropDownMenu().clickLogoutButton();
      }
}
