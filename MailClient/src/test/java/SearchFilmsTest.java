import org.apache.log4j.Level;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.catalogsFilmsPage.AuthorizationPage;
import page.catalogsFilmsPage.MainCatalogsPage;
import utils.BaseTest;
import utils.Proper;
import utils.enums.CATEGORIES;
import utils.enums.RESULTSCOUNT;
import utils.enums.SORTS;

import java.util.Iterator;
import java.util.List;


/**
 * User: dsobol
 */
public class SearchFilmsTest extends BaseTest {

  private AuthorizationPage authorizationPage;
  private MainCatalogsPage mainCatalogsPage;

  private final static String SEARCH_TITLE = "title";

  @BeforeTest
  public void beforeTest() {
    authorizationPage = factory.createPage(AuthorizationPage.class);
    mainCatalogsPage =  authorizationPage.Login(Proper.GetProperty("sAdminLogin"),
        Proper.GetProperty("sAdminPassword"));
    Assert.assertTrue(mainCatalogsPage.isUserAuthorization(), "Пользователь не смог авторизоваться");
    mainCatalogsPage.setDefaultSettingsForSearch();
    Assert.assertEquals(mainCatalogsPage.getCurrentCategoryForSearch(), CATEGORIES.ALL.getFullName(),
        String.format("Фильтр по категории не была установлен на '%s'", CATEGORIES.ALL.getFullName()));
    Assert.assertEquals(mainCatalogsPage.getCurrentSortForSearch(), SORTS.AZ.getFullName(),
        String.format("Фильтр по сортировке не был установлен на '%s'", SORTS.AZ.getFullName()));
    Assert.assertEquals(mainCatalogsPage.getCurrentCountResultsForSearch(), RESULTSCOUNT.ALL.getFullName(),
        String.format("Фильтр по количеству результатов в поиске не был установлен на '%s'", RESULTSCOUNT.ALL.getFullName()));
  }

  @Test()
  public void checkResultTest() {
    checkCorrectResults(mainCatalogsPage.searchFilms(SEARCH_TITLE).getAllTitlesOfFoundFilms(SEARCH_TITLE));
  }

  private void checkCorrectResults(List<String> list) {
    if(list.size() == 0)
      return;
    log.log(Level.INFO, "Проверяем корректность найденных результатов");
    Iterator<String> it = list.iterator();
    while(it.hasNext()) {
      String sTemp = it.next();
      log.log(Level.INFO, String.format("Проверяем вхождения искомого слова '%s' в найденном названии фильма '%s'",
          SEARCH_TITLE, sTemp));
      Assert.assertTrue(sTemp.toLowerCase().contains(SEARCH_TITLE.toLowerCase()),
          String.format("В найденном названии фильма '%s', нет вхождения искомого слова '%s'", sTemp, SEARCH_TITLE));
      log.log(Level.INFO, "Вхождение найдено. Корректно.");
    }

  }


}
