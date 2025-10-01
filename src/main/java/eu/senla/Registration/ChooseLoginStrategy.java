package eu.senla.Registration;

import com.codeborne.selenide.Selenide;
import eu.senla.Endpoints.Endpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChooseLoginStrategy {
        private static final Logger log = LoggerFactory.getLogger(ChooseLoginStrategy.class);

        public final void chooseLoginStrategy() {
            try {
                init();
                String strategy = getLoginStrategyType();
                log.info("Running authentication with strategy: {}", strategy);

                LoginStrategy authenticate = createLoginStrategy(strategy);
                authenticate.login();

            } catch (Exception e) {
                log.error("Authentication failed with strategy: {}", Endpoints.LOGIN_STRATEGY, e);
                throw new RuntimeException("Login failed: " + e.getMessage(), e);
            }
        }

        private String getLoginStrategyType() {
            return Endpoints.LOGIN_STRATEGY != null ? Endpoints.LOGIN_STRATEGY.trim() : "UI";
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
