package selenium;

import page.BasePage;

/**
 * User: dsobol
 */
public class PageFactory {

  public <T extends BasePage> T createPage(Class<T> cl) {
    T page;
    try {
      page = cl.newInstance();
    } catch(Exception exc) {
      throw new RuntimeException(exc);
    }
    return page;
  }

}
