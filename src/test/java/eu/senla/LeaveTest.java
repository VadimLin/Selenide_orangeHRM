package eu.senla;

import eu.senla.Leave.LeavePage;
import eu.senla.PimPage.PimPage;
import eu.senla.PropertyFile.ReadPropertyFile;
import eu.senla.Utils.FakerUtil.FakerUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class LeaveTest extends BaseTest {
  @Epic("Leave tab")
  @Feature("Add Assign Leave")
  @Story("User is able to add new Assign Leave")
  @Description("Verify that user is able to Add new Assign Leave with valid information")
  @Severity(SeverityLevel.CRITICAL)
  @Test
  public void addAssignLeaveTest() throws InterruptedException {

    PimPage pimPage = new PimPage();
    String firstName = new FakerUtil().generateRandomFirstName();
    String lastName = new FakerUtil().generateRandomLastName();
    String middleName = new FakerUtil().generateRandomMiddleName();
    String fullName = firstName + " " + middleName + " " + lastName;

    pimPage
        .navigateToPimModule()
        .clickAddEmployee()
        .fillEmployeeDetails(firstName, middleName, lastName)
        .saveEmployee();

    LeavePage leavePage = new LeavePage();
    final int defaultEntitlementDays = 123;
    leavePage
        .navigateToLeavePage()
        .openEntitlementsMenu()
        .clickAddEntitlements()
        .fillEmployeeName(fullName)
        .clickListbox()
        .openLeaveTypeDropDown(ReadPropertyFile.getProperty("LEAVE_TYPE_DROPDOWN"))
        .fillEntitlementField(defaultEntitlementDays)
        .clickSaveButton()
        .clickConfirmButton()
        .openAssignLeaveMenu()
        .fillEmployeeName(fullName)
        .clickListbox()
        .openLeaveTypeDropDown(ReadPropertyFile.getProperty("LEAVE_TYPE_DROPDOWN"))
        .inputDateFrom(ReadPropertyFile.getProperty("INPUT_DATE_FROM"))
        .inputDateTo(ReadPropertyFile.getProperty("INPUT_DATE_TO"))
        .clickAssignButton()
        .isConfirmed();
  }
}
