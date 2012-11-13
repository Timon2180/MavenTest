package pack_page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;

// ������ � ������� ��� ������� ���� �������
// �������

public class Page_Stargate extends Page
{
	@FindBy(xpath="//a[@href='/stargate/?logout=true']")
	private WebElement wLinkLogout;
	
	@FindBy(xpath="//ul[@class='x-tree-root-ct x-tree-arrows']/div/li[4]/div/a/span")
	private WebElement wAdvert;
	
	@FindBy(xpath="//ul[@class='x-tree-root-ct x-tree-arrows']/div/li[4]/ul/li[4]/div/a/span")
	private WebElement wCreatePremium;
	
	@SuppressWarnings("unused")
	@FindBy(xpath="//ul[@class='x-tree-root-ct x-tree-lines']/li/div/a/span")
	private WebElement wCategoryCatalog;
	
	@FindBy(xpath="//ul[@class='x-tree-root-ct x-tree-lines']/li/ul/li/div/a/span")
	private WebElement wAutoMain;
	
	@FindBy(xpath="//a[@class='x-tree-node-anchor']/span[contains(text(),'�������� ����������')]")
	private WebElement wEasyCar;		
	
	@FindBy(xpath="//a[@class='x-tree-node-anchor']/span[contains(text(),'���������� � ��������')]")
	private WebElement wEasyCarOld;

	@FindBy(xpath="//a[@class='x-tree-node-anchor']/span[contains(text(),'���� � ��������')]")
	private WebElement wEasyCarOld_1;
	
	@FindBy(xpath="//div[contains(text(),'���� � ���� -> �������� ���������� -> ���������� � ��������')]")
	private WebElement wFieldRubricAuto;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[2]/table/tbody/tr/td[3]")
	private WebElement wDivRegion;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[2]/div/input")
	private WebElement wInputRegion;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[2]/div/img")
	private WebElement wImageRegion;
	
	@FindBy(xpath="//fieldset[@class=' x-fieldset x-fieldset-noborder x-form-label-left']/div/div/div/div/div/input[2]")
	private WebElement wWindowFieldRegion;
	
	@FindBy(xpath="//div[@class='x-window-footer']/div/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button")
	private WebElement wButtonSaveRegion;
	
	@FindBy(xpath="//div[contains(text(),'������  (������)')]")
	private WebElement wListSaveRegion;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[5]/table/tbody/tr/td[3]")
	private WebElement wDivPrice;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[3]/input")
	private WebElement wInputPrice;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[6]/table/tbody/tr/td[3]")
	private WebElement wDivCurrency;
	
	@FindBy(xpath="//div[@class='x-form-field-wrap x-trigger-wrap-focus']/img")
	private WebElement wImageCurrency;
	
	@FindBy(xpath="//div[contains(text(),'���.')]")
	private WebElement wListCurrency;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[8]/table/tbody/tr/td[3]")
	private WebElement wDivEmail;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[5]/input")
	private WebElement wInputEmail;
	
	@FindBy(xpath="//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[19]/table/tbody/tr/td[3]")
	private WebElement wDivMake;
	
	/*
	 	//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div  / ������ ��� /div[1,2,3,4,5 � � �]/ � ������� ���� ������ 
	 	 ����� /table/tbody/tr/td[3]/
	 */
	

	public Page_Stargate(WebDriver driver)
	{
		super(driver);
	}
	
	public void OpenFormCreatePremium() throws Exception
	{
		Sleep(1000);
		System.out.println("��������� ����� ��");
		CheckElementPresent(1, "//ul[@class='x-tree-root-ct x-tree-arrows']/div/li[4]/div/a/span");
		System.out.println("�����");
		wAdvert.click();
		CheckElementPresent(1,"//ul[@class='x-tree-root-ct x-tree-arrows']/div/li[4]/ul/li[4]/div/a/span");
		System.out.println("��������� ����� �������� ��������");
		wCreatePremium.click();
		
		Sleep(1000);
		CheckElementPresent(1,"//ul[@class='x-tree-root-ct x-tree-lines']/li/div/a/span");
		CheckElementPresent(1,"//ul[@class='x-tree-root-ct x-tree-lines']/li/ul/li/div/a/span");
		DoubleClickElement(wAutoMain);
		CheckElementPresent(1,"//a[@class='x-tree-node-anchor']/span[contains(text(),'�������� ����������')]");
		DoubleClickElement(wEasyCar);
		CheckElementPresent(1,"//a[@class='x-tree-node-anchor']/span[contains(text(),'���������� � ��������')]");
		DoubleClickElement(wEasyCarOld);
		CheckElementPresent(1,"//a[@class='x-tree-node-anchor']/span[contains(text(),'���� � ��������')]");
		DoubleClickElement(wEasyCarOld_1);
		CheckElementPresent(1,"//div[contains(text(),'���� � ���� -> �������� ���������� -> ���������� � ��������')]");
		
		Sleep(1500);
		
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[2]/table/tbody/tr/td[3]");
		ClickElement(wDivRegion);
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[2]/div/img");
		ClickElement(wImageRegion);
		 
		CheckElementPresent(1,"//fieldset[@class=' x-fieldset x-fieldset-noborder x-form-label-left']/div/div/div/div/div/input[2]");
		wWindowFieldRegion.sendKeys("������");
		CheckElementPresent(1,"//body/div[@class='x-layer x-combo-list ']");
		wListSaveRegion.click();
		CheckElementPresent(1,"//div[@class='x-window-footer']/div/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button");
		wButtonSaveRegion.click();
		
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[5]/table/tbody/tr/td[3]");
		wDivPrice.click();
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[3]/input");
		wInputPrice.sendKeys("100");
		
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[6]/table/tbody/tr/td[3]");
		wDivCurrency.click();
		
		Sleep(1000);
		CheckElementPresent(1,"//div[@class='x-form-field-wrap x-trigger-wrap-focus']/img");
		wImageCurrency.click();
		
		CheckElementPresent(1,"//div[contains(text(),'���.')]");
		wListCurrency.click();
		
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[8]/table/tbody/tr/td[3]");
		wDivEmail.click();
		
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div[5]/input");
		wInputEmail.clear();
		wInputEmail.sendKeys("testcountop@yopmail.com");
		
		CheckElementPresent(1,"//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[19]/table/tbody/tr/td[3]");
		System.out.println("hello "+wInputEmail.getLocation());
		
		
		
		Locatable hoverItem = (Locatable)driver.findElement(By.xpath("//div[@id='propspanel']/div/div/div/div[2]/div/div/div/div[2]/div/div[18]/table/tbody/tr/td[3]"));
	    int y = hoverItem.getCoordinates().getLocationOnScreen().getY();
	    System.out.println(y);
	    //((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+100+");");
	    
	    //location_once_scrolled_into_view
	    /*Locatable hoverItem = (Locatable)driver.findElement(By.xpath("//div[@class='x-panel x-grid-panel ']"));
	    int y = hoverItem.getCoordinates().getLocationOnScreen().getY();
	    System.out.println(y);*/
	    //((JavascriptExecutor)driver).executeScript("window.scrollBy(0,0)", "");
	
	   // ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+y+");");
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivEmail.sendKeys(Keys.ARROW_DOWN);
	    Sleep(1000);
	    wDivMake.click();
	    
		//wLinkLogout.click();
	    
	    

	}
	
	@Override
	public void OpenPage()
	{
		
	}
}
