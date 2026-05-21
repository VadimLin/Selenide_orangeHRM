package eu.senla;

import com.codeborne.selenide.Selenide;
import eu.senla.LogoutPage.LogoutPage;
import eu.senla.PropertyFile.ReadPropertyFile;
import eu.senla.Registration.ChooseLoginStrategy;
import eu.senla.Utils.ConfigurationUtil.Configuration;
import groovy.util.logging.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseTest {
  protected String login = ReadPropertyFile.getProperty("USERNAME");
  protected String password = ReadPropertyFile.getProperty("PASSWORD");
  protected ChooseLoginStrategy loginStrategy;

  @BeforeMethod
  public void setup() {
    Configuration.setup();
//    loginStrategy = new ChooseLoginStrategy();
//    loginStrategy.chooseLoginStrategy();
  }

  @AfterMethod
  public void tearDown() {
    Selenide.closeWebDriver();
  }

  public void logoutUser() {
    new LogoutPage().openDropDownMenu().clickLogoutButton();
  }
}
