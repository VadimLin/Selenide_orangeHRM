package eu.senla;

import com.codeborne.selenide.Selenide;
import eu.senla.LoginPage.LoginPage;
import eu.senla.LogoutPage.LogoutPage;
import eu.senla.PropertyFile.ReadPropertyFile;
import eu.senla.Utils.ConfigurationUtil.Configuration;
import groovy.util.logging.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseTest {
  protected String login = ReadPropertyFile.getProperty("USERNAME");
  protected String password = ReadPropertyFile.getProperty("PASSWORD");

  @BeforeMethod
  public void setup() {
    Configuration.setup();
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
