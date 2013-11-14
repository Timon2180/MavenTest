package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * User: dsobol
 */
public class Proper {
  protected static Properties prop;

  static {
    prop = new Properties();
    try {
      prop.load(new FileReader("./src/main/resources/config.properties"));
    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

  public static String GetProperty(String sKey) {
    return prop.getProperty(sKey);
  }
}
