package eu.senla.DashboardPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import eu.senla.BasePage.BasePage;
import io.qameta.allure.Step;

public class DashboardPage extends BasePage {
  private final SelenideElement title =
      $(
          "a[class='oxd-main-menu-item active'] span[class='oxd-text oxd-text--span oxd-main-menu-item--name']");
  private final SelenideElement timeAtWorkWidget =
      $x("//div[contains(@class,' orangehrm-dashboard-widget')]" + "//p[text()='Time at Work']");
  private final SelenideElement myActionsWidget =
      $x("//div[contains(@class,' orangehrm-dashboard-widget')]" + "//p[text()='My Actions']");
  private final SelenideElement quickLaunchWidget =
      $x("//div[contains(@class,' orangehrm-dashboard-widget')]" + "//p[text()='Quick Launch']");
  private final SelenideElement buzzLatestPostsWidget =
      $x(
          "//div[contains(@class,' orangehrm-dashboard-widget')]"
              + "//p[text()='Buzz Latest Posts']");
  private final SelenideElement employeesOnLeaveTodayWidget =
      $x(
          "//div[contains(@class,' orangehrm-dashboard-widget')]"
              + "//p[text()='Employees on Leave Today']");
  private final SelenideElement employeeDistributionBySubWidget =
      $x(
          "//div[contains(@class,' orangehrm-dashboard-widget')]"
              + "//p[text()='Employee Distribution by Sub Unit']");
  private final SelenideElement employeeDistributionByLocationWidget =
      $x(
          "//div[contains(@class,' orangehrm-dashboard-widget')]"
              + "//p[text()='Employee Distribution by Sub Unit']");

  @Step("Check that widget timeAtWorkWidgetIsExists is exist on the page")
  public DashboardPage timeAtWorkWidgetIsExists() {
    timeAtWorkWidget.shouldBe(visible);
    return this;
  }

  @Step("Check that widget myActionsWidgetIsExists is exist on the page")
  public DashboardPage myActionsWidgetIsExists() {
    myActionsWidget.shouldBe(visible);
    return this;
  }

  @Step("Check that widget quickLaunchWidgetIsExists is exist on the page")
  public DashboardPage quickLaunchWidgetIsExists() {
    quickLaunchWidget.shouldBe(visible);
    return this;
  }

  @Step("Check that widget buzzLatestPostsWidgetIsExists is exist on the page")
  public DashboardPage buzzLatestPostsWidgetIsExists() {
    buzzLatestPostsWidget.shouldBe(visible);
    return this;
  }

  @Step("Check that widget employeesOnLeaveTodayWidgetIsExists is exist on the page")
  public DashboardPage employeesOnLeaveTodayWidgetIsExists() {
    employeesOnLeaveTodayWidget.shouldBe(visible);
    return this;
  }

  @Step("Check that widget employeeDistributionBySubWidgetIsExists is exist on the page")
  public DashboardPage employeeDistributionBySubWidgetIsExists() {
    employeeDistributionBySubWidget.shouldBe(visible);
    return this;
  }

  @Step("Check that widget employeeDistributionByLocationWidgetIsExists is exist on the page")
  public DashboardPage employeeDistributionByLocationWidgetIsExists() {
    employeeDistributionByLocationWidget.shouldBe(visible);
    return this;
  }

  public String getTitle() {
    return title.shouldBe(visible).getText();
  }
}
