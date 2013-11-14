package page;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.testng.annotations.Test;
import selenium.DriverConstruct;

import org.apache.log4j.Logger;
import utils.ThreadLogger;

/**
 * User: dsobol
 */
public class BasePage {

  protected DriverConstruct driver = DriverConstruct.getDriver();
  protected Logger log  = ThreadLogger.getThreadLogger();

}
