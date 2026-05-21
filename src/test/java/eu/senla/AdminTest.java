package eu.senla;

import com.codeborne.selenide.WebDriverRunner;
import eu.senla.AdminPage.AdminPage;
import eu.senla.Endpoints.Endpoints;
import eu.senla.PropertyFile.ReadPropertyFile;
import eu.senla.SideElement.SideElement;
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

public class AdminTest extends BaseTest {

  @Epic("Admin tab")
  @Feature("Open admin tab")
  @Story("User is able to open Admin tab with valid information on the page")
  @Description("Verify that user is able to open Admin tab with valid information")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Check Admin Page", groups = "smoke")
  public void adminTest() {
    AdminPage adminPage = new SideElement().navigateToAdminPage();
    SoftAssert sa = new SoftAssert();
    Allure.step("Validate title name", () -> sa.assertEquals(adminPage.getAdminTitle(), "Admin"));
    Allure.step(
        "Validate url",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("BASEURL") + Endpoints.ADMIN_ENDPOINT,
                WebDriverRunner.url(),
                "Incorrect URL"));
    sa.assertAll();
    logoutUser();
  }

  @Epic("Admin tab")
  @Feature("Add new Job Title")
  @Story("User is able to add new Job Title")
  @Description("Verify that user is able to Add new Job Title with valid information")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Add Job Title")
  public void addJobTitle() {
    AdminPage adminPage =
        new SideElement()
            .navigateToAdminPage()
            .clickDropDownMenu()
            .clickJobTitlesOption()
            .clickAddButton()
            .fillJobTitlefield(new FakerUtil().generateRandomTitle())
            .saveJobTitle()
            .isConfirmedMessage();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate title name", () -> sa.assertEquals(adminPage.getJobTitle(), "Job Titles"));
    Allure.step(
        "Validate url",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("BASEURL") + Endpoints.JOB_ENDPOINT,
                WebDriverRunner.url(),
                "Incorrect URL"));
    sa.assertAll();
    logoutUser();
  }

  @Epic("Admin tab")
  @Feature("Delete existing Job Title")
  @Story("User is able to delete existing Job Title")
  @Description("Verify that user is able to delete existing Job Title with")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Delete existing Job Title")
  public void deleteJobTitle() {
    String jobTitleName = new FakerUtil().generateRandomTitle();
    AdminPage adminPage =
        new SideElement()
            .navigateToAdminPage()
            .clickDropDownMenu()
            .clickJobTitlesOption()
            .clickAddButton()
            .fillJobTitlefield(jobTitleName)
            .saveJobTitle()
            .isConfirmedMessage()
            .deleteExistingJobTitle(jobTitleName)
            .confirmDelete()
            .isConfirmDeleteMessage();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate title name", () -> sa.assertEquals(adminPage.getJobTitle(), "Job Titles"));
    Allure.step(
        "Validate url",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("BASEURL") + Endpoints.JOB_ENDPOINT,
                WebDriverRunner.url(),
                "Incorrect URL"));
    logoutUser();
  }
}
