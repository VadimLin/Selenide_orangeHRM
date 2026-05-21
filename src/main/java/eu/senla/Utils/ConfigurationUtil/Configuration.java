package eu.senla.Utils.ConfigurationUtil;

import com.codeborne.selenide.logevents.SelenideLogger;
import eu.senla.PropertyFile.ReadPropertyFile;
import io.qameta.allure.selenide.AllureSelenide;

public class Configuration {
  public static void setup() {
    com.codeborne.selenide.Configuration.browser = ReadPropertyFile.getProperty("BROWSER");
    com.codeborne.selenide.Configuration.timeout =
        Long.parseLong(ReadPropertyFile.getProperty("TIMEOUT"));
    com.codeborne.selenide.Configuration.browserSize = ReadPropertyFile.getProperty("BROWSER.SIZE");
    com.codeborne.selenide.Configuration.reportsFolder = "target/allure-results";
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    //    Configuration.remote = ReadPropertyFile.getProperty("selenoidUIUrl");
    //    Configuration.headless = false;
  }
}
