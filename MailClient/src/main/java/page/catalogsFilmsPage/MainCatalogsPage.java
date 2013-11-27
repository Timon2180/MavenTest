package page.catalogsFilmsPage;

import org.apache.log4j.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import page.BasePage;
import utils.enums.CATEGORIES;
import utils.enums.RESULTSCOUNT;
import utils.enums.SORTS;

/**
 * User: dsobol
 */
public class MainCatalogsPage extends BasePage {

  private static final String LOG_OUT_LINK_XPATH = "//a[contains(@href, '?logout')]";
  private static final String MY_PROFILE_LINK_XPATH = "//a[contains(@href, '?go=profile')]";
  private static final String CURRENT_TYPE_CATEGORY_LINK_XPATH = "//a[@id='category-button']";
  private static final String CURRENT_TYPE_SORT_LINK_XPATH = "//a[@id='sort-button']";
  private static final String CURRENT_TYPE_COUNT_RESULT_LINK_XPATH = "//a[@id='n-button']";

  private static final String BLOCK_WITH_LIST_OF_CATEGORY_XPATH =  "//ul[@id='category-menu']";
  private static final String BLOCK_WITH_LIST_OF_SORT_XPATH = "//ul[@id='sort-menu']";
  private static final String BLOCK_WITH_LIST_OF_COUNT_RESULT_XPATH = "//ul[@id='n-menu']";
  private static final String ITEM_IN_LIST_XPATH_PATTERN = "//a[text()='%s']";

  private static final String FIELD_SEARCH_XPATH = "//div[@class='searchbox']/input";


  public boolean isUserAuthorization() {
    log.log(Level.INFO, "Проверям авторизовался ли пользователь по наличию ссылки 'My profile' и 'Log out'");
    boolean bResult = driver.isElementPresentAndVisible(LOG_OUT_LINK_XPATH) & driver.isElementPresentAndVisible(MY_PROFILE_LINK_XPATH);
    log.log(Level.INFO, "Результат =>" + bResult);
    return bResult;
  }

  public MainCatalogsPage setDefaultSettingsForSearch() {
    log.log(Level.INFO, "Сбрасываем настройки фильтров поиска, на настройки по умолчанию, очищаем поле поиска.");
    setCategoryForSearch(CATEGORIES.ALL);
    setSortForSearch(SORTS.AZ);
    setCountResultsForSearch(RESULTSCOUNT.ALL);
    driver.typeByXpath(FIELD_SEARCH_XPATH, "");
    driver.waitLoadjQueryElement();
    return this;
  }

  public MainCatalogsPage setCategoryForSearch(CATEGORIES category) {
    log.log(Level.INFO, "Устанавливаем категорию поиска.");
    log.log(Level.INFO, String.format("Нажимаем на текущее значение категории поиска, элемент с xpath=%s",
        CURRENT_TYPE_CATEGORY_LINK_XPATH));
    driver.clickElementByXpath(CURRENT_TYPE_CATEGORY_LINK_XPATH);
    log.log(Level.INFO, String.format("В открывшемся списке выбираем значение '%s', элемент с xpath=%s",category.getFullName(),
        String.format(BLOCK_WITH_LIST_OF_CATEGORY_XPATH + ITEM_IN_LIST_XPATH_PATTERN, category.getFullName())));
    driver.clickElementByXpath(String.format(BLOCK_WITH_LIST_OF_CATEGORY_XPATH + ITEM_IN_LIST_XPATH_PATTERN, category.getFullName()));
    return this;
  }

  public MainCatalogsPage setSortForSearch(SORTS sort) {
    log.log(Level.INFO, "Устанавливаем сортировку результатов поиска.");
    log.log(Level.INFO, String.format("Нажимаем на текущее значение сортировки результатов поиска, элемент с xpath=%s",
        CURRENT_TYPE_SORT_LINK_XPATH));
    driver.clickElementByXpath(CURRENT_TYPE_SORT_LINK_XPATH);
    log.log(Level.INFO, String.format("В открывшемся списке выбираем значение '%s', элемент с xpath=%s",sort.getFullName(),
        String.format(BLOCK_WITH_LIST_OF_SORT_XPATH + ITEM_IN_LIST_XPATH_PATTERN, sort.getFullName())));
    driver.clickElementByXpath(String.format(BLOCK_WITH_LIST_OF_SORT_XPATH + ITEM_IN_LIST_XPATH_PATTERN, sort.getFullName()));
    return this;
  }

  public MainCatalogsPage setCountResultsForSearch(RESULTSCOUNT resultsCount) {
    log.log(Level.INFO, "Устанавливаем количество элементов на страницу в результатах поиска.");
    log.log(Level.INFO, String.format("Нажимаем на текущее значение количество элементов в результатах поиска, элемент с xpath=%s",
        CURRENT_TYPE_COUNT_RESULT_LINK_XPATH));
    driver.clickElementByXpath(CURRENT_TYPE_COUNT_RESULT_LINK_XPATH);
    log.log(Level.INFO, String.format("В открывшемся списке выбираем значение '%s', элемент с xpath=%s",resultsCount.getFullName(),
        String.format(BLOCK_WITH_LIST_OF_COUNT_RESULT_XPATH + ITEM_IN_LIST_XPATH_PATTERN, resultsCount.getFullName())));
    driver.clickElementByXpath(String.format(BLOCK_WITH_LIST_OF_COUNT_RESULT_XPATH + ITEM_IN_LIST_XPATH_PATTERN, resultsCount.getFullName()));
    return this;
  }

  public String getCurrentCategoryForSearch() {
    log.log(Level.INFO, String.format("Получаем текущее значение категории поиска из элемента с xpath=%s",
        CURRENT_TYPE_CATEGORY_LINK_XPATH));
    String sResult = driver.getText(CURRENT_TYPE_CATEGORY_LINK_XPATH);
    log.log(Level.INFO, "Результат =>" + sResult);
    return sResult;
  }

  public String getCurrentSortForSearch() {
    log.log(Level.INFO, String.format("Получаем текущее значение сортировки поиска из элемента с xpath=%s",
        CURRENT_TYPE_SORT_LINK_XPATH));
    String sResult = driver.getText(CURRENT_TYPE_SORT_LINK_XPATH);
    log.log(Level.INFO, "Результат =>" + sResult);
    return sResult;
  }

  public String getCurrentCountResultsForSearch() {
    log.log(Level.INFO, String.format("Получаем текущее значение количество элементов в результатах поиска из элемента с xpath=%s",
        CURRENT_TYPE_COUNT_RESULT_LINK_XPATH));
    String sResult = driver.getText(CURRENT_TYPE_COUNT_RESULT_LINK_XPATH);
    log.log(Level.INFO, "Результат =>" + sResult);
    return sResult;
  }

  public ResultSearchPage searchFilms(String sTitle) {
    log.log(Level.INFO, String.format("Производим поиск фильма, вводим в элемент с xpath=%s, искомое название '%s' и нажимаем Enter",
        FIELD_SEARCH_XPATH, sTitle));
    driver.typeByXpath(FIELD_SEARCH_XPATH, sTitle);
    Actions actions  = new Actions(driver);
    actions.sendKeys(Keys.RETURN).build().perform();
    driver.waitLoadjQueryElement();
    return factory.createPage(ResultSearchPage.class);
  }

}
