package eu.senla;

import com.codeborne.selenide.WebDriverRunner;
import eu.senla.DashboardPage.DashboardPage;
import eu.senla.Endpoints.Endpoints;
import eu.senla.PropertyFile.ReadPropertyFile;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DashBoardTest extends BaseTest {
  @Epic("Dashboard tab")
  @Feature("Check widgets on the Dashboard page")
  @Story("User is able to open Dashboard tab and find widget he need on the page")
  @Description("Verify that user to open Dashboard tab and find widget he need on the page")
  @Severity(SeverityLevel.NORMAL)
  @Test(description = "Check existing widgets on DashboardPage")
  public void dashboardTest() {
    DashboardPage dashboardPage = new DashboardPage();
    loginAsUser();
    SoftAssert sa = new SoftAssert();
    Allure.step(
        "Validate title name", () -> sa.assertEquals(dashboardPage.getTitle(), "Dashboard"));
    dashboardPage
        .timeAtWorkWidgetIsExists()
        .myActionsWidgetIsExists()
        .quickLaunchWidgetIsExists()
        .buzzLatestPostsWidgetIsExists()
        .employeesOnLeaveTodayWidgetIsExists()
        .employeeDistributionBySubWidgetIsExists()
        .employeeDistributionByLocationWidgetIsExists();
    Allure.step(
        "Validate title name", () -> sa.assertEquals(dashboardPage.getTitle(), "Dashboard"));
    Allure.step(
        "Validate title name",
        () ->
            sa.assertEquals(
                ReadPropertyFile.getProperty("BASEURL") + Endpoints.DASHBOARD_ENDPOINT,
                    WebDriverRunner.url(),
                "Incorrect URL"));
    sa.assertAll();
    logoutUser();
  }
}
