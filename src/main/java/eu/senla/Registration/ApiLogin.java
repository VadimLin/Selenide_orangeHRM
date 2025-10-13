package eu.senla.Registration;

import com.codeborne.selenide.WebDriverRunner;
import eu.senla.Client.LoginHelper;
import eu.senla.PropertyFile.ReadPropertyFile;
import org.openqa.selenium.Cookie;

public class ApiLogin implements LoginStrategy {

  private String targetUrl;

  public ApiLogin(String url) {
    this.targetUrl = url;
  }

  public final void login() {
    Cookie cookie =
        new Cookie.Builder("orangehrm", LoginHelper.getCookie())
            .domain(ReadPropertyFile.getProperty("DOMAIN"))
            .path("/web")
            .isHttpOnly(true)
            .sameSite("Lax")
            .build();

    WebDriverRunner.getWebDriver().manage().deleteCookieNamed("orangehrm");
    WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    WebDriverRunner.getWebDriver().get(this.targetUrl);
  }
}
