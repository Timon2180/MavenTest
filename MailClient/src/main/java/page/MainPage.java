package page;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import utils.enums.FOLDERS;

/**
 * User: dsobol
 */
public class MainPage extends BasePage {

  private static String INBOX_NAME_XPATH_PATTERN = "//span[@class='rtIn' and text()='%s']";


  public MainPage openFolder(FOLDERS folders) {
    log.log(Level.INFO, String.format("Открываем папку %s, xpath = %s", folders.getFullName(),
        String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName())));
    driver.clickElementByXpath(String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName()));
    return this;
  }

  public boolean isChooseFolder(FOLDERS folders) {
    log.log(Level.INFO, String.format("Проверяем открыта ли папка %s, xpath = %s", folders.getFullName(),
        String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName())));
    boolean bFlag = driver.isElementPresentAndVisible(String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName()), "background-color", "rgba(196, 221, 252, 1)");
    log.log(Level.INFO, "Результат =>" + bFlag);
    return bFlag;
  }

}
