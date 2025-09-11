package eu.senla.BasePage;

import eu.senla.PropertyFile.ReadPropertyFile;

import static com.codeborne.selenide.Selenide.open;

public class BasePage {
  public static final String LOGIN_URL = ReadPropertyFile.getProperty("BASEURL");

  public void navigateTo(String endpoint) {
    open(LOGIN_URL + endpoint);
  }
}
