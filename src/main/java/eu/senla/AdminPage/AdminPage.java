package eu.senla.AdminPage;

import com.codeborne.selenide.SelenideElement;
import eu.senla.BasePage.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AdminPage extends BasePage {

  private final SelenideElement adminModuleLink = $x("//a[@href='/web/index.php/admin/viewAdminModule']");

  private final SelenideElement jobDropDown = $x("//span[normalize-space()='Job']");
  private final SelenideElement jobTitlesOption = $x("//a[normalize-space()='Job Titles']");
  private final SelenideElement addButton = $x("//i[@class='oxd-icon bi-plus oxd-button-icon']");
  private final SelenideElement jobTitle = $x("//h6[normalize-space()='Job Titles']");
  private final SelenideElement jobTitleField =
          $x(
          "//div[@class='oxd-input-group oxd-input-field-bottom-space']"
              + "//div//input[@class='oxd-input oxd-input--active']");
  private final SelenideElement saveButton = $("button[type='submit']");
  private final SelenideElement confirmationMessage =
          $x("//div[@id='oxd-toaster_1']//p[text()='Success']");
  private final SelenideElement adminTitle =
      $(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
  private final SelenideElement confirmDeleteButton = $x("//button[normalize-space()='Yes, Delete']");
  private final SelenideElement confirmDeleteMessage =
          $x("//div[@id='oxd-toaster_1']//p[text()='Success']");

  @Step("Navigate to Admin Module")
  public AdminPage navigateToAdminModule() {
    adminModuleLink.shouldBe(visible).click();
    return this;
  }

  public String getAdminTitle() {
    return adminTitle.shouldBe(visible).getText();
  }

  @Step("Click Dropdown Menu")
  public AdminPage clickDropDownMenu() {
    jobDropDown.shouldBe(visible).click();
    return this;
  }

  @Step("Click JobTitles option")
  public AdminPage clickJobTitlesOption() {
    jobTitlesOption.shouldBe(visible).click();
    return this;
  }

  @Step("Click add button")
  public AdminPage clickAddButton() {
    addButton.shouldBe(visible).click();
    return this;
  }

  @Step("Fill JobTitle field")
  public AdminPage fillJobTitlefield(String jobTitle) {
    jobTitleField.shouldBe(visible).sendKeys(jobTitle);
    return this;
  }

  @Step("Click Save button")
  public AdminPage saveJobTitle() {
    saveButton.shouldBe(visible).click();
    return this;
  }


  public String getJobTitle() {
    return jobTitle.shouldBe(visible).getText();
  }

  @Step("Confirmation Message")
  public AdminPage isConfirmedMessage() {
    confirmationMessage.shouldBe(visible);
    return new AdminPage();
  }

  @Step("Delete existing Job Title")
  public AdminPage deleteExistingJobTitle(String jobTitle) {
    SelenideElement deleteJobButton =
        $x(
            "//div[contains(text(),\""
                + jobTitle
                + "\")]"
                + "/parent::div/following-sibling::div//child::i[@class='oxd-icon bi-trash']");
    deleteJobButton.shouldBe(visible).click();
    return this;
  }

  @Step("Click delete confirmation")
  public AdminPage confirmDelete() {
   confirmDeleteButton.shouldBe(visible).click();
    return this;
  }

  @Step("Delete confirmation")
  public AdminPage isConfirmDeleteMessage() {
    confirmDeleteMessage.shouldBe(visible);
    return this;
  }
}
