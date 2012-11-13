package pack_page;
import pack_utils.ExceptFailTest;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page
{
	protected WebDriver driver;
	
	public Page(WebDriver driver){this.driver=driver;}

	// ����� �������� ��������
	public abstract void OpenPage();
	
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
		else System.out.println("OKKKKKK");
			
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
		wElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(sLocator)));
		if(wElement==null)
			throw new ExceptFailTest("������� "+sLocator+" ������, �� �� ��������");
			
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

}

