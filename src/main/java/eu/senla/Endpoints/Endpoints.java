package eu.senla.Endpoints;

import eu.senla.PropertyFile.ReadPropertyFile;

public class Endpoints {
  public static final String DASHBOARD_ENDPOINT = "/dashboard/index";
  public static final String PIM_ENDPOINT = "/pim/viewPersonalDetails/empNumber";
  public static final String AUTH_ENDPOINT = "/auth/login";
  public static final String ADMIN_ENDPOINT = "/admin/viewSystemUsers";
  public static final String JOB_ENDPOINT = "/admin/viewJobTitleList";
  public static final String CANDIDATE_ENDPOINT = "/recruitment/addCandidate";
  public static final String LEAVE_ENDPOINT = "/leave/assignLeave";
  public static final String MAIN_URL = ReadPropertyFile.getProperty("MAIN_URL");
  public static final String WEB_EP = "/web/index.php";
  public static final String AUTH_LOGIN_URL = "/auth/login";
  public static final String AUTH_VALIDATE_URL = "/auth/validate";
  public static final String DASHBOARD_URL = "/dashboard/index";
}
