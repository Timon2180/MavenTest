package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Proper;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: dsobol
 */
public class DriverConstruct extends RemoteWebDriver {

    private String sBaseUrl;
    private static ThreadLocal<DriverConstruct> webDriverLocal = new ThreadLocal<DriverConstruct>();

    public DriverConstruct(URL urlRemoteServer, DesiredCapabilities capabilities) {
        super(urlRemoteServer, capabilities);
        //manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        manage().window().maximize();
        sBaseUrl = Proper.GetProperty("sBaseUrl");
        get(sBaseUrl);
    }

    public static DriverConstruct getDriver() {
        DriverConstruct driver;
        if ((driver = webDriverLocal.get()) == null) {
            try {
                driver = new DriverConstruct(new URL(Proper.GetProperty("sServerGrid")), DesiredCapabilities.firefox());
            } catch (MalformedURLException exc) {
                exc.printStackTrace();
            }
            webDriverLocal.set(driver);
            return driver;
        } else return driver;
    }

    public static void stopDriver(WebDriver driver) {
        if (driver != null) {
            webDriverLocal.remove();
            driver.quit();
        }
    }

    public void clickElementByXpath(String sXpath) {
        waitElementPresentAndVisible(sXpath, 5);
        waitElementEnabled(sXpath, 3);
        click(findElementByXPath(sXpath));
    }

    public boolean isElementPresentAndVisible(String sXpath) {
        return (findElementsByXPath(sXpath).size() > 0) && findElementByXPath(sXpath).isDisplayed();
    }

    public boolean isElementClickable(String sLocator) {
        WebDriverWait wait = new WebDriverWait(this, 5);
        try {
             wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sLocator)));
        } catch (TimeoutException exc) {
            return false;
        }
        return true;
    }


    public boolean isElementDisappear(String sLocator) {
        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(this, 5);
        try {
            bFlag = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(sLocator)));
        } catch (TimeoutException exc) {
            return bFlag;
        }
        return bFlag;
    }


    public boolean isElementPresentAndVisible(String sXpath, String sCssProperty, String sCssExpected) {
        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(this, 5);
        try {
            bFlag = wait.until(isDisplayedVisibilityExpectedCondition(sXpath, sCssProperty, sCssExpected));
        } catch (TimeoutException exc) {
            return bFlag;
        }
        return bFlag;
    }

    private void waitElementPresentAndVisible(String sLocator, int nTimeout) {
        WebDriverWait wait = new WebDriverWait(this, nTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sLocator)));
    }

    private void waitElementEnabled(String sXpath, int nTimeout) {
        while (!isEnabledByXpath(sXpath) && nTimeout-- > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
    }

    public boolean isEnabledByXpath(String xpath) {
        return findElementByXPath(xpath).isEnabled();
    }

    private void click(WebElement wElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this, 5);
        webDriverWait.until(ExpectedConditions.visibilityOf(wElement));
        wElement.click();
    }

    private ExpectedCondition<Boolean> isDisplayedVisibilityExpectedCondition(final String xpath, final String sCssProperty,
                                                                              final String sCssExpected) {
        ExpectedCondition<Boolean> elementVisibleAndDisplayed = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return driver.findElement(By.xpath(xpath)).getCssValue(sCssProperty).equals(sCssExpected);
                } catch (NoSuchElementException exc) {
                    return false;
                }
            }
        };
        return elementVisibleAndDisplayed;
    }


}
