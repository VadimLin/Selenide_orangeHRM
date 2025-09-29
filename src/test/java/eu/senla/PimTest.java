package eu.senla;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import eu.senla.Endpoints.Endpoints;
import eu.senla.PimPage.PimPage;
import eu.senla.PropertyFile.ReadPropertyFile;
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

public class PimTest extends BaseTest {
  @Epic("PIM tab")
  @Feature("Add employee")
  @Story("User is  able to add new employee")
  @Description("Verify that user is able to add new employee to the app")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Check successful adding of employee")
  public void testAddEmployee() {

    PimPage pimPage = new PimPage();
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String middleName = new FakerUtil().generateRandomMiddleName();

    loginAsUser();
    pimPage
        .navigateToPimModule()
        .clickAddEmployee()
        .fillEmployeeDetails(firstName, middleName, lastName)
        .saveEmployee();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate url",
        () ->
            sa.assertTrue(
                            WebDriverRunner.url()
                    .contains(ReadPropertyFile.getProperty("BASEURL") + Endpoints.PIM_ENDPOINT),
                "Incorrect Url"));
    Allure.step("Validate title name", () -> sa.assertEquals(pimPage.getTitle(), "PIM"));
    sa.assertAll();
    logoutUser();
  }

  @Epic("PIM tab")
  @Feature("Add employee")
  @Story("User is not able to add new employee with empty First Name field")
  @Description(
      "Verify that user is not able to add new employee to the app with empty First Name field")
  @Severity(SeverityLevel.NORMAL)
  @Test
  public void testEmptyFirstNameField() {
    PimPage pimPage = new PimPage();
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String middleName = new FakerUtil().generateRandomMiddleName();

    loginAsUser();
    pimPage
        .navigateToPimModule()
        .clickAddEmployee()
        .fillEmployeeDetails(firstName, middleName, lastName)
        .saveEmployee()
        .clearFirstName();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate required alert",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("REQUIRED_ALERT"), pimPage.getRequiredAlert()));
    Allure.step(
        "Validate url",
        () ->
            sa.assertTrue(
                            WebDriverRunner.url()
                    .contains(ReadPropertyFile.getProperty("BASEURL") + Endpoints.PIM_ENDPOINT),
                "Incorrect Url"));
    Allure.step(
        "Validate personal title name",
        () -> sa.assertEquals(pimPage.getPersonalDetailTitle(), "Personal Details"));
    sa.assertAll();
  }

  @Epic("PIM tab")
  @Feature("Add employee")
  @Story("User is not able to add new employee with empty Last Name field")
  @Description(
      "Verify that user is not able to add new employee to the app with empty Last Name field")
  @Severity(SeverityLevel.NORMAL)
  @Test
  public void testEmptyLastNameField() {
    PimPage pimPage = new PimPage();
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String middleName = new FakerUtil().generateRandomMiddleName();

    loginAsUser();
    pimPage
        .navigateToPimModule()
        .clickAddEmployee()
        .fillEmployeeDetails(firstName, middleName, lastName)
        .saveEmployee()
        .clearLastName();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate required alert",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("REQUIRED_ALERT"), pimPage.getRequiredAlert()));
    Allure.step(
        "Validate url",
        () ->
            sa.assertTrue(
                            WebDriverRunner.url()
                    .contains(ReadPropertyFile.getProperty("BASEURL") + Endpoints.PIM_ENDPOINT),
                "Incorrect Url"));
    Allure.step(
        "Validate personal title name",
        () -> sa.assertEquals(pimPage.getPersonalDetailTitle(), "Personal Details"));
    sa.assertAll();
  }


  @Epic("PIM tab")
  @Feature("Add employee")
  @Story("User is not able to add new employee with empty both First and Last Name field")
  @Description(
      "Verify that user is not able to add new employee to the app with empty both First and Last Name field")
  @Severity(SeverityLevel.NORMAL)
  @Test
  public void testEditFirstNameAndLastNameFields() {
    PimPage pimPage = new PimPage();
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String middleName = new FakerUtil().generateRandomMiddleName();

    loginAsUser();
    pimPage
        .navigateToPimModule()
        .clickAddEmployee()
        .fillEmployeeDetails(firstName, middleName, lastName)
        .saveEmployee()
        .clearFirstName()
        .clearLastName()
        .fillFirstName(firstName)
        .fillLastName(lastName)
        .clickSaveDetailFormButton();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate url",
        () ->
            sa.assertTrue(
                            WebDriverRunner.url()
                    .contains(ReadPropertyFile.getProperty("BASEURL") + Endpoints.PIM_ENDPOINT),
                "Incorrect Url"));
    Allure.step(
        "Validate personal title name",
        () -> sa.assertEquals(pimPage.getPersonalDetailTitle(), "Personal Details"));
    sa.assertAll();
  }
}
