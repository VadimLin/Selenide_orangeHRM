package eu.senla.Registration;

import eu.senla.LoginPage.LoginPage;
import eu.senla.PropertyFile.ReadPropertyFile;

public class UIFormLogin implements LoginStrategy {
  @Override
  public final void login() throws Exception {
    new LoginPage()
        .login(ReadPropertyFile.getProperty("USERNAME"), ReadPropertyFile.getProperty("PASSWORD"));
  }
}
