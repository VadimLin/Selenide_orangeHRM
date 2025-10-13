package eu.senla.RecruitmentPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import eu.senla.BasePage.BasePage;
import io.qameta.allure.Step;

public class RecruitmentPage extends BasePage {

  private final SelenideElement recruitModuleLink =
      $x(
          "//span[@class='oxd-text oxd-text--span "
              + "oxd-main-menu-item--name'][normalize-space()='Recruitment']");
  private final SelenideElement recruitTitle =
      $(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");

  private final SelenideElement addButton =
      $x("//div[@class='orangehrm-header-container']/child::button");
  private final SelenideElement firstNameField = $x("//input[@placeholder='First Name']");
  private final SelenideElement middleNameField = $x("//input[@placeholder='Middle Name']");
  private final SelenideElement lastNameField = $x("//input[@placeholder='Last Name']");
  private final SelenideElement dropDownVacancyMenu =
      $x("//div[@class='oxd-select-text--after']/child::i");
  private final SelenideElement listOfVacancies = $("div[role='listbox'] div:nth-of-type(3) span");
  private final SelenideElement emailField =
      $x("//label[contains(text(),'Email')]/parent::div/following::div[1]/input");
  private final SelenideElement contactNumberField =
      $x("//label[text()='Contact Number']/parent::div/following::div[1]/input");
  private final SelenideElement keywordsField =
      $x("//input[@placeholder='Enter comma seperated words...']");
  private final SelenideElement notesField = $x("//textarea[@placeholder='Type here']");
  private final SelenideElement saveButton = $("button[type='submit']");
  private final SelenideElement confirmMessage =
      $x("//div[@id='oxd-toaster_1']//p[text()='Success']");
  private final SelenideElement alertMessage =
      $x("//span[contains(@class, 'oxd-input-group__message')]");
  private final SelenideElement alertEmailMessage =
      $x(
          "//input[@class='oxd-input oxd-input--active "
              + "oxd-input--error']/ancestor::div/span[contains(@class, 'oxd-input-group__message')]");

  public String getTitle() {
    return recruitTitle.shouldBe(visible).getText();
  }

  @Step("Click save button")
  public RecruitmentPage clickAddButton() {
    addButton.shouldBe(visible).click();
    return this;
  }

  @Step("Fill First Name field")
  public RecruitmentPage enterFirstName(String firstname) {
    firstNameField.shouldBe(visible).setValue(firstname);
    return this;
  }

  @Step("Fill Middle Name field")
  public RecruitmentPage enterMiddleName(String middlename) {
    middleNameField.shouldBe(visible).setValue(middlename);
    return this;
  }

  @Step("Fill Last Name field")
  public RecruitmentPage enterLastName(String lastname) {
    lastNameField.shouldBe(visible).setValue(lastname);
    return this;
  }

  @Step("Open dropdown vacancy menu")
  public RecruitmentPage openDropDownMenu() {
    dropDownVacancyMenu.shouldBe(visible).click();
    return this;
  }

  @Step("Click on vacancy")
  public RecruitmentPage chooseFromListVacancies() {
    listOfVacancies.shouldBe(visible).click();
    return this;
  }

  @Step("Fill email field")
  public RecruitmentPage enterEmail(String email) {
    emailField.shouldBe(visible).setValue(email);
    return this;
  }

  @Step("Fill contact number field")
  public RecruitmentPage enterContactNumber(String number) {
    contactNumberField.shouldBe(visible).setValue(number);
    return this;
  }

  @Step("Fill keywords field")
  public RecruitmentPage enterKeywords(String keywords) {
    keywordsField.shouldBe(visible).setValue(keywords);
    return this;
  }

  @Step("Fill notes field")
  public RecruitmentPage enterNotes(String notes) {
    notesField.shouldBe(visible).setValue(notes);
    return this;
  }

  @Step("Confirmation message")
  public RecruitmentPage isConfimed() {
    confirmMessage.shouldBe(visible);
    return this;
  }

  @Step("Click save button")
  public RecruitmentPage clickSaveButton() {
    saveButton.shouldBe(visible).click();
    return this;
  }

  @Step("Fill required fields")
  public RecruitmentPage fillOnlyRequiredCandidateFields(
      String firstName, String lastName, String email) {
    firstNameField.shouldBe(visible).setValue(firstName);
    lastNameField.shouldBe(visible).setValue(lastName);
    emailField.shouldBe(visible).setValue(email);
    saveButton.shouldBe(visible).click();
    return this;
  }

  public String getEmailAlertText() {
    return alertEmailMessage.shouldBe(visible).getText();
  }

  public String getRequiredAlert() {
    return alertMessage.shouldBe(visible).getText();
  }
}
