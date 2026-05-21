package eu.senla.Registration;

import com.codeborne.selenide.Selenide;
import eu.senla.Endpoints.Endpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChooseLoginStrategy {
  private static final Logger LOG = LoggerFactory.getLogger(ChooseLoginStrategy.class);

  public final void chooseLoginStrategy() {
    try {
      init();
      String strategy = getLoginStrategyType();
      LOG.info("Running authentication with strategy: {}", strategy);

      LoginStrategy authenticate = createLoginStrategy(strategy);
      authenticate.login();

    } catch (Exception e) {
      LOG.error("Authentication failed with strategy: {}", Endpoints.LOGIN_STRATEGY, e);
      throw new RuntimeException("Login failed: " + e.getMessage(), e);
    }
  }

  private String getLoginStrategyType() {
    if (Endpoints.LOGIN_STRATEGY != null) {
      return Endpoints.LOGIN_STRATEGY.trim();
    } else {
      return "UI";
    }
  }

  private LoginStrategy createLoginStrategy(String strategy) {
    String targetUrl = Endpoints.MAIN_URL + Endpoints.WEB_EP + Endpoints.DASHBOARD_URL;

    if ("API".equalsIgnoreCase(strategy)) {
      return new ApiLogin(targetUrl);
    } else {
      return new UIFormLogin();
    }
  }

  final void init() {
    String loginUrl = Endpoints.MAIN_URL + Endpoints.WEB_EP + Endpoints.AUTH_LOGIN_URL;
    Selenide.open(loginUrl);
  }
}
