package eu.senla.Leave;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LeavePage {
  private final SelenideElement leaveModuleLink = $x("//a[@href='/web/index.php/leave/viewLeaveModule']");
  private final SelenideElement assignLeaveMenu =
          $x("//a[@class='oxd-topbar-body-nav-tab-item'][text()='Assign Leave']");
  private final SelenideElement leaveTitle = $x("//p[@class='oxd-text oxd-text--p orangehrm-main-title']");
  private final SelenideElement assignEmployeeNameField =
          $x("//div[@class='oxd-autocomplete-text-input--before']/following::input[1]");
  private final SelenideElement assignEmployeeSearchBox =
      $("div[role='listbox'] div:nth-of-type(1) span");

  private final SelenideElement assignLeaveTypeField = $x("//div[contains(text(),'-- Select --')]");

  private final SelenideElement entitlementsDropDown =
          $x("//span[normalize-space()='Entitlements']//i[@class='oxd-icon bi-chevron-down']");
  private final SelenideElement addEntitlementsButton = $x("//a[text()='Add Entitlements']");

  private final SelenideElement entitlementField =
          $x(
          "//div[@class='oxd-input-group oxd-input-field-bottom-space']"
              + "//div//input[@class='oxd-input oxd-input--active']");
  private final SelenideElement saveButton = $("button[type='submit']");

  private final SelenideElement confirmButton = $x("//button[normalize-space()='Confirm']");
  private final SelenideElement fromDateInput =
          $x("//div[@class='oxd-autocomplete-text-input--before']/following::input[2]");
  private final SelenideElement toDateInput =
          $x("//div[@class='oxd-autocomplete-text-input--before']/following::input[3]");
  private final SelenideElement assignButton = $x("//button[@type='submit']");
  private final SelenideElement confirmationMessage = $("#oxd-toaster_1");

  @Step("Navigate to Leave tab")
  public LeavePage navigateToLeavePage() {
    leaveModuleLink.shouldBe(visible).click();
    return this;
  }

  @Step("Click entitlements Dropdown")
  public LeavePage openEntitlementsMenu() {
    entitlementsDropDown.shouldBe(visible).click();
    return this;
  }

  @Step("Click add entitlements")
  public LeavePage clickAddEntitlements() {
    addEntitlementsButton.shouldBe(visible).click();
    return this;
  }

  @Step("Fill entitlement field")
  public LeavePage fillEntitlementField(int days) {
   entitlementField.shouldBe(visible).setValue(String.valueOf(days));
    return this;
  }

  @Step("Click save button")
  public LeavePage clickSaveButton() {
    saveButton.shouldBe(visible).click();
    return this;
  }

  @Step("Click confirmation button")
  public LeavePage clickConfirmButton() {
    confirmButton.shouldBe(visible).click();
    return this;
  }

  @Step("Click Assign Leave tab")
  public LeavePage openAssignLeaveMenu() {
    assignLeaveMenu.shouldBe(visible).click();
    return this;
  }

  @Step("Fill employee name field")
  public LeavePage fillEmployeeName(String employee) {
    assignEmployeeNameField.shouldBe(visible).setValue(employee);
    return this;
  }

  @Step("Click Leave Type")
  public LeavePage clickListbox() {
    assignEmployeeSearchBox.shouldBe(visible).click();
    return this;
  }

  @Step("Choose Leave Type")
  public LeavePage openLeaveTypeDropDown(String leaveType) {
    SelenideElement leaveTypeFromListbox = $x("//span[text()='" + leaveType + "']");
    assignLeaveTypeField.shouldBe(visible).click();
    leaveTypeFromListbox.shouldBe(visible).click();
    return this;
  }

  @Step("Fill From Date field")
  public final LeavePage inputDateFrom(String date) {
    fromDateInput.shouldBe(visible).setValue(date);
    return this;
  }

  @Step("Fill To Date field")
  public final LeavePage inputDateTo(String date) {
    toDateInput.shouldBe(visible).sendKeys(Keys.CONTROL + "a");
    toDateInput.shouldBe(visible).sendKeys(Keys.DELETE);
    toDateInput.shouldBe(visible).sendKeys(date);
    return this;
  }

  @Step("Click Assign button")
  public final LeavePage clickAssignButton() {
    assignButton.shouldBe(visible).click();
    return this;
  }

  @Step("Confirmation message")
  public final LeavePage isConfirmed() {
    confirmationMessage.shouldBe(visible);
    return this;
  }
}
