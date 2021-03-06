package page;

import selenium.DriverConstruct;

import org.apache.log4j.Logger;
import selenium.PageFactory;
import utils.ThreadLogger;

/**
 * User: dsobol
 */
public class BasePage {

  protected DriverConstruct driver = DriverConstruct.getDriver();
  protected Logger log  = ThreadLogger.getThreadLogger();
  protected PageFactory factory = new PageFactory();

}
