package eu.senla.SideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import eu.senla.AdminPage.AdminPage;
import eu.senla.DashboardPage.DashboardPage;
import eu.senla.Leave.LeavePage;
import eu.senla.PimPage.PimPage;
import eu.senla.RecruitmentPage.RecruitmentPage;
import io.qameta.allure.Step;

public class SideElement {
  private final SelenideElement recruitmentPage =
      $x(
          "//span[@class='oxd-text oxd-text--span "
              + "oxd-main-menu-item--name'][normalize-space()='Recruitment']");
  private final SelenideElement pimPage = $("a[href$='viewPimModule']");
  private final SelenideElement adminPage = $x("//a[@href='/web/index.php/admin/viewAdminModule']");
  private final SelenideElement dashBoardPage = $x("//a[@href='/web/index.php/dashboard/index']");
  private final SelenideElement leavePage = $x("//a[@href='/web/index.php/leave/viewLeaveModule']");

  @Step("Navigate to Leave tab")
  public LeavePage navigateToLeavePage() {
    leavePage.shouldBe(visible).click();
    return new LeavePage();
  }

  @Step("Navigate to Dashboard tab")
  public DashboardPage navigateToDashboardPage() {
    dashBoardPage.shouldBe(visible).click();
    return new DashboardPage();
  }

  @Step("Navigate to admin tab")
  public AdminPage navigateToAdminPage() {
    adminPage.shouldBe(visible).click();
    return new AdminPage();
  }

  @Step("Navigate to pim tab")
  public PimPage navigateToPimPage() {
    pimPage.shouldBe(visible).click();
    return new PimPage();
  }

  @Step("Navigate to recruitment tab")
  public RecruitmentPage navigateToRecruitmentPage() {
    recruitmentPage.shouldBe(visible).click();
    return new RecruitmentPage();
  }
}
