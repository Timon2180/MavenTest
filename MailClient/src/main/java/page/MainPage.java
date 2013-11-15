package page;

import org.apache.log4j.Level;
import utils.enums.FOLDERS;

/**
 * User: dsobol
 */
public class MainPage extends BasePage {

  private static final String INBOX_NAME_XPATH_PATTERN = "//span[@class='rtIn' and text()='%s']";
  private static final String PLACE_HOLDER_XPATH = "//div[@class='raDiv']";
  private static final String LINK_SORT_FROM_XPATH = "//th[@class='rgHeader']/a[text()='From']";


  public MainPage openFolder(FOLDERS folders) {
    log.log(Level.INFO, String.format("Открываем папку %s, xpath = %s", folders.getFullName(),
        String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName())));
    driver.clickElementByXpath(String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName()));
    return this;
  }

  public boolean isChooseFolder(FOLDERS folders) {
    log.log(Level.INFO, String.format("Проверяе выбрана ли папка %s, xpath = %s", folders.getFullName(),
        String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName())));
    boolean bFlag = driver.isElementPresentAndVisible(String.format(INBOX_NAME_XPATH_PATTERN, folders.getFullName()),
        "background-color", "rgba(196, 221, 252, 1)");
    log.log(Level.INFO, "Результат =>" + bFlag);
    return bFlag;
  }

  public boolean isFolderLoaded(FOLDERS folders) {
    log.log(Level.INFO, String.format("Проверяем  загрузились ли данные для папки %s", folders.getFullName()));
    boolean bFlag = driver.isElementDisappear(PLACE_HOLDER_XPATH);
    log.log(Level.INFO, "Результат =>" + bFlag);
    return bFlag;
  }

  public boolean isLinkSortClickabledAndEnabled() {
    log.log(Level.INFO, String.format("Проверяем доступна ли ссылка сортировки писем 'FROM' с xpath = %s",
        LINK_SORT_FROM_XPATH));
    boolean bFlag = driver.isElementClickable(LINK_SORT_FROM_XPATH);
    log.log(Level.INFO, "Результат =>" + bFlag);
    return bFlag;
  }

}
