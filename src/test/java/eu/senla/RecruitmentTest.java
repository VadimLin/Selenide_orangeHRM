package eu.senla;

import com.codeborne.selenide.WebDriverRunner;
import eu.senla.DataProviders.ProjectDataProvider;
import eu.senla.Endpoints.Endpoints;
import eu.senla.PropertyFile.ReadPropertyFile;
import eu.senla.RecruitmentPage.RecruitmentPage;
import eu.senla.Utils.FakerUtil.FakerUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RecruitmentTest extends BaseTest {
  @Epic("Recruitment tab")
  @Feature("Add candidate")
  @Story("User is  able to add new candidate with all fields")
  @Description("Verify that user is able to add new candidate to the app with all fields")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Successful add candidate with all fields")
  public void addCandidateTest() {
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String middleName = new FakerUtil().generateRandomMiddleName();
    String email = new FakerUtil().generateRandomEmailAddress();
    String contactNumber = new FakerUtil().generateRandomPhoneNumber();
    String keywords = new FakerUtil().generateRandomKeywords();
    String notes = new FakerUtil().generateRandomNotes();

    String correctContactNumber = contactNumber.replaceAll("[^0-9+\\-\\/()]", "");

    RecruitmentPage recruitmentPage = new RecruitmentPage();
    loginAsUser();
    recruitmentPage
        .navigateToRecruitModule()
        .clickAddButton()
        .enterFirstName(firstName)
        .enterMiddleName(middleName)
        .enterLastName(lastName)
        .openDropDownMenu()
        .chooseFromListVacancies()
        .enterEmail(email)
        .enterContactNumber(correctContactNumber)
        .enterKeywords(keywords)
        .enterNotes(notes)
        .clickSaveButton()
        .isConfimed();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate title name", () -> sa.assertEquals(recruitmentPage.getTitle(), "Recruitment"));
    Allure.step(
        "Validate url",
        () ->
            sa.assertTrue(
                            WebDriverRunner.url()
                    .contains(
                        ReadPropertyFile.getProperty("BASEURL") + Endpoints.CANDIDATE_ENDPOINT),
                "Incorrect Url"));
    sa.assertAll();
    logoutUser();
  }

  @Epic("Recruitment tab")
  @Feature("Add candidate")
  @Story("User is  able to add new candidate with required fields")
  @Description("Verify that user is able to add new candidate to the app with required fields")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Successful adding only with required fields")
  public void successfulAddCandidateOnlyWithRequiredFields() {
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String email = new FakerUtil().generateRandomEmailAddress();

    RecruitmentPage recruitmentPage = new RecruitmentPage();
    loginAsUser();
    recruitmentPage
        .navigateToRecruitModule()
        .clickAddButton()
        .fillOnlyRequiredCandidateFields(firstName, lastName, email)
        .isConfimed();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate title name", () -> sa.assertEquals(recruitmentPage.getTitle(), "Recruitment"));
    Allure.step(
        "Validate url",
        () ->
            sa.assertTrue(
                            WebDriverRunner.url()
                    .contains(
                        ReadPropertyFile.getProperty("BASEURL") + Endpoints.CANDIDATE_ENDPOINT),
                "Incorrect Url"));
    sa.assertAll();
    logoutUser();
  }

  @Epic("Recruitment tab")
  @Feature("Add candidate")
  @Story("User is not able to add new candidate with invalid data in fields")
  @Description(
      "Verify that user is not able to add new candidate to the app with with invalid data in fields")
  @Severity(SeverityLevel.CRITICAL)
  @Test(
      description =
          "Check adding candidate with valid firstName and lastName, and invalid email in {0}",
      dataProvider = "getRecruitmentCredentials",
      dataProviderClass = ProjectDataProvider.class)
  public void addCandidateWithInvalidData(
      String description, String firstname, String lastname, String email) {
    RecruitmentPage recruitmentPage = new RecruitmentPage();
    loginAsUser();
    recruitmentPage
        .navigateToRecruitModule()
        .clickAddButton()
        .fillOnlyRequiredCandidateFields(firstname, lastname, email);
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate alert text",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("EMAIL_ALERT"), recruitmentPage.getEmailAlertText()));
    Allure.step(
        "Validate url",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("BASEURL") + Endpoints.CANDIDATE_ENDPOINT,
                    WebDriverRunner.url(),
                "Url doesn't match"));
    sa.assertAll();
    logoutUser();
  }


  @Epic("Recruitment tab")
  @Feature("Add candidate")
  @Story("User is not able to add new candidate with empty data in fields")
  @Description(
      "Verify that user is not able to add new candidate to the app with with empty data in fields")
  @Severity(SeverityLevel.NORMAL)
  @Test(
      description = "Check adding candidate with empty {0}",
      dataProvider = "getRecruitmentEmptyCredentials",
      dataProviderClass = ProjectDataProvider.class)
  public void addCandidateWithEmptyData(
      String description, String firstname, String lastname, String email) {
    RecruitmentPage recruitmentPage = new RecruitmentPage();
    loginAsUser();
    recruitmentPage
        .navigateToRecruitModule()
        .clickAddButton()
        .fillOnlyRequiredCandidateFields(firstname, lastname, email);
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate alert text",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("REQUIRED_ALERT"),
                recruitmentPage.getRequiredAlert()));
    Allure.step(
        "Validate url",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("BASEURL") + Endpoints.CANDIDATE_ENDPOINT,
                    WebDriverRunner.url(),
                "Url doesn't match"));
    sa.assertAll();
    logoutUser();
  }
}