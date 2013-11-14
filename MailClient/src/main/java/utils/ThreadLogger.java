package utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * User: dsobol
 */
public class ThreadLogger {
  private static ThreadLocal<Logger> loggerHolder = new ThreadLocal<Logger>();

  public static Logger getThreadLogger() {
    Logger log;
    if ((log = loggerHolder.get()) == null) {
      log = Logger.getLogger(String.valueOf(Thread.currentThread().getId()));
      loggerHolder.set(log);
      log.setLevel(Level.INFO);
      log.addAppender(new ConsoleAppender(new PatternLayout("%d %l %m %n")));
      return log;
    }
    return log;
  }
}
