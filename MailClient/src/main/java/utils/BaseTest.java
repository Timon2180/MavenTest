package utils;

import org.apache.log4j.Logger;
import selenium.PageFactory;

/**
 * User: dsobol
 */
public class BaseTest {

  protected PageFactory factory = new PageFactory();
  protected Logger log  = ThreadLogger.getThreadLogger();

}
