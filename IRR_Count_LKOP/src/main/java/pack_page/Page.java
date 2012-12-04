package pack_page;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import pack_utils.ExceptFailTest;
import pack_utils.WriterLog;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page
{
	protected WebDriver driver;
	protected WriterLog wLog;
	
	public Page(WebDriver driver)
	{
		this.driver = driver;
	}

	
	public void GetWriterLog(WriterLog wLog)
	{
		this.wLog = wLog;
	}
	
	// ����� �������� ��������
	public abstract void OpenPage() throws ExceptFailTest;
	
	// ����� �������� ��� � �������� ���� ������� � ������� ��������� (��������� ����� � �������� ����� ������ ���� �������� 
	// ��������� �������� ��������)
	// sAtribute - ������� ������� ( � ������� class)
	// sFindAtribute - �������� ��������
	// wElement - ������� � �������� �� ������� ����� ��� ��������
	public void CheckAtributeElement(final String sAtribute, final String sFindAtribute, final WebElement wElement) throws ExceptFailTest
	{
		Boolean bFlag = false;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, 10);
		try
		{
			bFlag = wWaitDriver.until(new ExpectedCondition<Boolean>()
					{
						public Boolean apply(WebDriver wd)
						{
							return wElement.getAttribute(sAtribute).equals(sFindAtribute);
						}
					}
								  	  );
		}
		catch(TimeoutException exc){System.out.println("������� c ��������� "+sFindAtribute+" �� ������");}
		if(!bFlag)
		{
			throw new ExceptFailTest("������� c ��������� "+sFindAtribute+" �� ������");
		}
		//else System.out.println("OKKKKKK");
			
	}
	
	public void CheckCssElement(final String sCssAtribute, final String sFindCssAtributeValue, final WebElement wElement) throws ExceptFailTest
	{
		Boolean bFlag = false;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, 10);
		try
		{
			bFlag = wWaitDriver.until(new ExpectedCondition<Boolean>()
					{
						public Boolean apply(WebDriver wd)
						{
							return wElement.getCssValue(sCssAtribute).equals(sFindCssAtributeValue);
						}
					}
								  	  );
		}
		catch(TimeoutException exc){System.out.println("������� c CSS "+sFindCssAtributeValue+" �� ������");}
		if(!bFlag)
		{
			throw new ExceptFailTest("������� c CSS "+sFindCssAtributeValue+" �� ������");
		}
		//else System.out.println("OKKKKKK2");
			
	}
	
	// ����� �������� ������������� ��������
	public void CheckElementPresent (final int nKey, final String sLocator) throws ExceptFailTest
	{
		WebElement wElement = null;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, 10);
		try
		{
		wElement = wWaitDriver.until(new ExpectedCondition<WebElement>()
				{
					public WebElement apply(WebDriver wd)
					{
					WebElement wEl = null;
						switch (nKey)
						{
							case 1:
								wEl =  wd.findElement(By.xpath(sLocator));
								break;
							case 2:
								wEl = wd.findElement(By.id(sLocator));
								break;
							case 3:
								wEl = wd.findElement(By.name(sLocator));
								break;
							case 4:
								wEl = wd.findElement(By.className(sLocator));
								break;
							case 5:
								wEl = wd.findElement(By.linkText(sLocator));
								break;
							case 6:
								wEl = wd.findElement(By.cssSelector(sLocator));
								break;
						}
						return wEl;
					}
				}								
											  );
		}
		catch(TimeoutException exc){System.out.println("������� "+sLocator+" �� ������");}
		
		if(wElement == null)
		{
			throw new ExceptFailTest("������� "+sLocator+" �� ������");
		}
	}
	
	// ����� �������� ��� ������� ���������� � �������� // ���������� �� ��� �������� � �� ���������
	public void CheckElementEnabled (String sLocator) throws ExceptFailTest 
	{
		WebElement wElement = null;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sLocator)));
		if(wElement==null)
			throw new ExceptFailTest("������� "+sLocator+" ������, �� �� ��������");
		System.out.println(wElement.getText());
			
	}
	
	// ����� ��������� �������� i ���������� �����������
	public void Sleep(int i)
	{
		try{Thread.sleep(i);}catch(InterruptedException exc){exc.printStackTrace();}
	}
	
	// ����� ��������� �� ��������
	public void DoubleClickElement(WebElement wElement) 
	{
		Actions builder;
		Action dClick;
		builder = new Actions(driver);
		builder.doubleClick(wElement); // ������� ������������������ ��������
		dClick = builder.build(); // �������� ���� ��������
		dClick.perform(); // ��������� ��������
	}
	
	// ����� ����� �� �������� (���� �� �������� ������� click)
	public void ClickElement(WebElement wElement) 
	{
		System.out.println(wElement.getLocation());
		Actions builder;
		Action cClick;
		builder = new Actions(driver);
		builder.click(wElement); // ������� ������������������ ��������
		cClick = builder.build(); // �������� ���� ��������
		cClick.perform(); // ��������� ��������
	}
	
	// ����� ������� ������� 
	public void KeyPress(WebElement wElement, Keys key,  int n)
	{
		for(int i=0; i<n; i++)
		{
			wElement.sendKeys(key);
			Sleep(200);
		}	
	}

	// ������ � ��������
	public void ScrollToElement(WebElement wElement)
	{
		
		((Locatable)wElement).getLocationOnScreenOnceScrolledIntoView();	
	}
	
	
	// ������ ���������
	public void CaptureScreenshot(String name)
	{
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "src\\" + screenshot.getName();
        System.out.println(path);
       
        try
        {
            FileUtils.copyFile(screenshot, new File("src\\screenshot.png"));
        } 
        catch (IOException e) {}
    }
	
	public void TakeScreenShotMethod() // ���� ���������� �� ������ ����� ������ � ������������ ��� �� ���
	{
	    try{
	        Thread.sleep(2000);
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        //BufferedImage image = new Robot().createScreenCapture(new Rectangle(0,0,300,300));
	        ImageIO.write(image, "jpg", new File("src\\screenshot2.png"));
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	/*	private boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch (NoSuchElementException exc) {return false;}
	}
*/
	
	
	/*
	 	Locatable hoverItem = (Locatable)driver.findElement(By.xpath("//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[18]/table/tbody/tr/td[3]"));
	  	int y = hoverItem.getCoordinates().getLocationOnScreen().getY();
	   	System.out.println(y);
	  	//((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+100+");");
	  	// ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+y+");");
*/
}

