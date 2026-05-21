package eu.senla.LogoutPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import eu.senla.BasePage.BasePage;
import io.qameta.allure.Step;

public class LogoutPage extends BasePage {
  private final SelenideElement dropDownMenu =
      $(".oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon");
  private final SelenideElement logoutButton = $x("//a[text()='Logout']");
  private final SelenideElement loginTitle = $x("//h5[text()='Login']");

  @Step("Click Dropdown menu ")
  public LogoutPage openDropDownMenu() {
    dropDownMenu.shouldBe(visible).click();
    return this;
  }

  @Step("Click Logout button")
  public LogoutPage clickLogoutButton() {
    logoutButton.shouldBe(visible).click();
    return this;
  }

  public String getLoginTitle() {
    return loginTitle.shouldBe(visible).getText();
  }
}
