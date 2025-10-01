package eu.senla.PimPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import eu.senla.BasePage.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PimPage extends BasePage {
  private final SelenideElement pimModuleLink = $("a[href$='viewPimModule']");
  private final SelenideElement addEmployeeButton =
      $x("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
  private final SelenideElement firstNameInput = $x("//input[@placeholder='First Name']");
  private final SelenideElement middleNameInput = $x("//input[@placeholder='Middle Name']");
  private final SelenideElement lastNameInput = $x("//input[@placeholder='Last Name']");
  private final SelenideElement saveButton = $("button[type='submit']");
  private final SelenideElement employeeListUrl =
      $x("//h6[@class='oxd-text oxd-text--h6 --strong']");
  private final SelenideElement dashboardIndicator =
      $x(
          "//a[@class='oxd-main-menu-item active']/span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']");
  private final SelenideElement personalDetailTitle = $x("//h6[text()='Personal Details']");
  private final SelenideElement alertMessage =
      $x("//span[contains(@class, 'oxd-input-group__message')]");
  private final SelenideElement saveDetailFormButton =
      $x(
          "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']"
              + "//button[@type='submit']");

  @Step("Navigate to Pim tab")
  public PimPage navigateToPimModule() {
    pimModuleLink.shouldBe(visible).click();
    return this;
  }

  @Step("Click Add button")
  public PimPage clickAddEmployee() {
    addEmployeeButton.shouldBe(visible).click();
    return this;
  }

  @Step("Fill First Name, Middle Name and Last Name fields")
  public PimPage fillEmployeeDetails(String firstName, String middleName, String lastName) {
    firstNameInput.shouldBe(visible).setValue(firstName);
    middleNameInput.shouldBe(visible).setValue(middleName);
    lastNameInput.shouldBe(visible).setValue(lastName);
    return this;
  }

  public String getTitle() {
    return dashboardIndicator.shouldBe(visible).getText();
  }

  @Step("Click save button")
  public PimPage saveEmployee() {
    saveButton.shouldBe(visible).click();
    employeeListUrl.shouldBe(visible);
    return this;
  }

  @Step("Fill First Name field")
  public PimPage fillFirstName(String firstName) {
    firstNameInput.shouldBe(visible).setValue(firstName);
    return this;
  }

  @Step("Fill Last Name field")
  public PimPage fillLastName(String lastName) {
    lastNameInput.shouldBe(visible).setValue(lastName);
    return this;
  }

  @Step("Clear First Name field")
  public PimPage clearFirstName() {
    SelenideElement firstNameElement = firstNameInput.shouldBe(visible);
    firstNameElement.click();
    firstNameElement.sendKeys(Keys.CONTROL, "a");
    firstNameElement.sendKeys(Keys.BACK_SPACE);
    return this;
  }

  @Step("Clear Last Name field")
  public PimPage clearLastName() {
    WebElement lastNameElement = lastNameInput.shouldBe(visible);
    lastNameElement.click();
    lastNameElement.sendKeys(Keys.CONTROL, "a");
    lastNameElement.sendKeys(Keys.BACK_SPACE);
    return this;
  }

  public String getRequiredAlert() {
    return alertMessage.shouldBe(visible).getText();
  }

  public String getPersonalDetailTitle() {
    return personalDetailTitle.shouldBe(visible).getText();
  }

  @Step("Click save button")
  public PimPage clickSaveDetailFormButton() {
    saveDetailFormButton.shouldBe(visible).click();
    return this;
  }
}
