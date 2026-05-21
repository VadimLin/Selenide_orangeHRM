package eu.senla.LoginPage;

import com.codeborne.selenide.SelenideElement;
import eu.senla.BasePage.BasePage;
import eu.senla.Endpoints.Endpoints;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
  private final SelenideElement usernameField = $x("//input[@name='username']");
  private final SelenideElement passwordField = $x("//input[@name='password']");
  private final SelenideElement submitButton = $("button");
  private final SelenideElement alertMessage =
      $x("//div/p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
  private final SelenideElement errorColor = $x("//input[@name='username']/following::span");
  private final SelenideElement dashboardIndicator =
      $x(
          "//a[@class='oxd-main-menu-item active']/span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']");

  public final LoginPage load() {
    super.navigateTo(Endpoints.AUTH_ENDPOINT);
    return this;
  }

  @Step("Enter Username")
  public final LoginPage enterUserName(String userName) {
    usernameField.shouldBe(visible).setValue(userName);
    return this;
  }

  @Step("Enter password")
  public final LoginPage enterPassword(String password) {
    passwordField.shouldBe(visible).setValue(password);
    return this;
  }

  @Step("Submit credentials")
  public final LoginPage clickSubmitButton() {
    submitButton.shouldBe(visible).click();
    return this;
  }

  @Step("Login to app")
  public LoginPage loginUI(String username, String password) {
    enterUserName(username).enterPassword(password).clickSubmitButton();
    return this;
  }

  public String getAlertText() {
    return alertMessage.shouldBe(visible).getText();
  }

  public String getErrorText() {
    return errorColor.shouldBe(visible).getText();
  }

  public String getErrorColor() {
    return errorColor.getCssValue("color");
  }

  @Step
  public LoginPage isLoginSuccessful() {
    dashboardIndicator.shouldBe(visible);
    return this;
  }
}
