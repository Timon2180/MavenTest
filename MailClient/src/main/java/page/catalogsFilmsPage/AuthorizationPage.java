package page.catalogsFilmsPage;

import org.apache.log4j.Level;
import page.BasePage;

/**
 * User: dsobol
 */
public class AuthorizationPage extends BasePage {

  private static final String USER_NAME_FIELD_XPATH = "//input[@name='username']";
  private static final String PASSWORD_FIELD_XPATH = "//input[@name='password']";
  private static final String SUBMIT_BUTTON_XPATH = "//input[@name='submit']";

  public MainCatalogsPage Login(String sUserName, String sPassword) {
    log.log(Level.INFO, String.format("Авторизуемся пользователем с логином '%s', и паролем '%s'", sUserName, sPassword));
    log.log(Level.INFO, String.format("Вводим логин в поле ,с xpath = %s", USER_NAME_FIELD_XPATH));
    driver.typeByXpath(USER_NAME_FIELD_XPATH, sUserName);
    log.log(Level.INFO, String.format("Вводим пароль в поле ,с xpath = %s", PASSWORD_FIELD_XPATH));
    driver.typeByXpath(PASSWORD_FIELD_XPATH, sPassword);
    log.log(Level.INFO, String.format("Нажимаем кнопку Log in ,с xpath = %s", SUBMIT_BUTTON_XPATH));
    driver.clickElementByXpath(SUBMIT_BUTTON_XPATH);
    return factory.createPage(MainCatalogsPage.class);
  }

}
