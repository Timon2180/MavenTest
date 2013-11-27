package page.catalogsFilmsPage;

import org.apache.log4j.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dsobol
 */
public class ResultSearchPage extends MainCatalogsPage {

  private static final String BLOCK_WITH_FIND_FILM_XPATH = "//div[@id='results']/a";
  private static final String NAME_FIND_FILM_XPATH_PATTERN = "//div[@id='results']/a[%d]//div[@class='title']";

  public List<String> getAllTitlesOfFoundFilms(String sTitle) {
    List<String> list = new ArrayList<String>();
    log.log(Level.INFO, "Получаем все названия найденных фильмов.");
    int nCount = driver.getElementsCount(BLOCK_WITH_FIND_FILM_XPATH);
    if(nCount == 0) {
      log.log(Level.INFO, String.format("При поиске по '%s' ничего не найдено.", sTitle));
      return list;
    }
    log.log(Level.INFO, "Найдено:");
    for (int i = 0; i < nCount; i++) {
      String sTemp = driver.getText(String.format(NAME_FIND_FILM_XPATH_PATTERN, (i+1)));
      log.log(Level.INFO, sTemp);
      list.add(sTemp);
    }
    return list;
  }

}
