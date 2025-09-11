package eu.senla;

import org.testng.annotations.DataProvider;

public class ProjectDataProvider {
  @DataProvider(name = "getCredentials")
  private static Object[][] getCredentials() {
    return new Object[][] {
      {"password", "Admin", "123"},
      {"username", "asdasda", "admin1234"},
      {"password and username", "adsdasd", "1234"}
    };
  }

  @DataProvider(name = "getEmptyCredentials")
  private static Object[][] getEmptyCredentials() {
    return new Object[][] {
      {"password", "Admin", ""},
      {"username", "", "admin1234"},
      {"password and username", "", ""}
    };
  }

  @DataProvider(name = "getRecruitmentCredentials")
  private static Object[][] getRecruitmentCredentials() {
    return new Object[][] {
      {"without @", "John", "Lee", "test.com"},
      {"without comma", "Jack", "Low", "test@testcom"},
      {"with dots in a row", "Adam", "Saimons", "test@test..com"},
      {"with @ in a row", "Charlie", "Coyle", "test@@test.com"}
    };
  }

  @DataProvider(name = "getRecruitmentEmptyCredentials")
  private static Object[][] getRecruitmentEmptyCredentials() {
    return new Object[][] {
      {"firstname", "", "Lee", "test@test.com"},
      {"lastname", "John", "", "test2@test.com"},
      {"email", "John", "Lee", ""},
      {"all fields", "", "", ""}
    };
  }
}
