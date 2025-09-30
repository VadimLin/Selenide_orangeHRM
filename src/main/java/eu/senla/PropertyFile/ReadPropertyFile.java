package eu.senla.PropertyFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
  private static Properties property;

  static {
    try {
      property = new Properties();
      FileInputStream propertyFile = new FileInputStream("src/test/resources/config.properties");
      property.load(propertyFile);
    } catch (IOException e) {
      System.err.println("Error: Properties file doesn't find!");
    }
  }

  public static String getProperty(String key) {
    return property.getProperty(key);
  }
}
