package pack_page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pack_utils.ExceptFailTest;
import pack_utils.Proper;

public class Page_IrrMain extends Page
{
	
	@FindBy(id="a_login")             
	private WebElement wLinkEnter;
	
	@FindBy(xpath="//div[@class='popupRegions']")
	private WebElement wWindowSelectOfRegion;
	
	@FindBy(xpath="//div[@class='button-style btn-a']/a")
	private WebElement wOkButtonWindowSelectOfRegion;
	
	@FindBy(xpath="//div[@id='popup-wrap']//input[@class='login']")
	private WebElement wFieldLoginForm;
	
	@FindBy(xpath="//div[@id='popup-wrap']//input[@name='password']")
	private WebElement wFieldPasswordForm;
	
	@FindBy(xpath="//div[@id='popup-wrap']//a[@class='loginFormSubmit']")
	private WebElement wButtonEnterForm;
	
	@FindBy(xpath="//a[@id='load_user_ads_counter']/span[2]")
	private WebElement wLinkPrivateOffice;
	
	//@FindBy(xpath="(//a[contains(@href, '/myadverts/')])[2]")
	@FindBy(xpath="//div[@id='block_links_lk']/ul/li/a")
	private WebElement wLinkMyAdverts;
	
	
	private String sUrlIRR;
	
	public Page_IrrMain(WebDriver driver){super(driver);}

	public void OpenPage() throws ExceptFailTest
	{
		sUrlIRR = Proper.GetProperty("urlIrr");
		driver.navigate().to(sUrlIRR);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wLog.WriteString(1, "��������� �������� "+sUrlIRR);
		System.out.println("��������� �������� "+sUrlIRR);
	}
	
	public void OpenFormAuthorization() throws ExceptFailTest
	{
		
		CheckElementPresent(1,"//div[@class='popupRegions']");
		if(wWindowSelectOfRegion.isDisplayed())
		{
			wLog.WriteString(1, "��������� ���� ������ ��������");
			System.out.println("��������� ���� ������ ��������");
			CheckElementPresent(1,"//div[@class='button-style btn-a']/a");
			wOkButtonWindowSelectOfRegion.click();
		}
		CheckElementPresent(2, "a_login");
		wLog.WriteString(1,"��������� ����� �����������");
		System.out.println("��������� ����� �����������");
		wLinkEnter.click();
		
	}
	
	public Page_IrrPrivateOffice LoginOn() throws ExceptFailTest
	{
		Sleep(1000);
		wLog.WriteString(1, "������ �����");
		System.out.println("������ �����");
		CheckElementPresent(1, "//div[@id='popup-wrap']//input[@class='login']");
		wFieldLoginForm.sendKeys(Proper.GetProperty("loginIRR"));
		wLog.WriteString(1, "������ ������");
		System.out.println("������ ������");
		CheckElementPresent(1, "//div[@id='popup-wrap']//input[@name='password']");
		wFieldPasswordForm.sendKeys(Proper.GetProperty("passwordIRR"));	
		CheckElementPresent(1, "//div[@id='popup-wrap']//a[@class='loginFormSubmit']");
		wButtonEnterForm.click();
		wLog.WriteString(1, "�������� �����");
		System.out.println("�������� �����");
		Sleep(1000);
		CheckElementPresent(1, "//a[@id='load_user_ads_counter']/span[2]");
		wLinkPrivateOffice.click();
		wLog.WriteString(1, "��������� � ������ �������");
		System.out.println("��������� � ������ �������");
		Sleep(1000);
		CheckElementPresent(1,"//div[@id='block_links_lk']/ul/li/a");	
		wLinkMyAdverts.click();
		driver.get(Proper.GetProperty("urlIrr")+"myadverts/");
		
		
		
		
		//CaptureScreenshot("gdf");
		//TakeScreenShotMethod();
		
		return PageFactory.initElements(driver, Page_IrrPrivateOffice.class);
		
		
	//	driver.findElement(By.xpath("//div[@id='block_links_lk']/ul/li/a")).click();
		
		
		
		//((JavascriptExecutor)driver).executeScript("alert('hello')");
		/*
		//this.KeyPress(wButtonEnterForm, Keys.ENTER, 1);
		//Sleep(2000);
		System.out.println("dfdfgd1");
		CheckElementPresent(2, "passport_userInfo"); // ��������� ��� ������������c� ���� ��� 
		System.out.println("dfdfgd2");
		System.out.println("dfdfgd3");
		
		CheckElementPresent(1, "//div[@id='block_links_lk']/ul/li/a");
		
		///System.out.println(wLinkMyAdverts.getAttribute("href"));
		//System.out.println(wLinkMyAdverts.getLocation());
		//this.ClickElement(wLinkMyAdverts);
		//wLinkMyAdverts.click();
		System.out.println("dfdfgd4");
		Robot robot = new Robot();
		robot.mouseMove(939,114+118);
		
		Actions builder;
		Action cClick;
		builder = new Actions(driver);
		builder.click();
		//builder.moveToElement(wLinkPrivateOffice2, 5, 25).click(); // ������� ������������������ ��������
		cClick = builder.build(); // �������� ���� ��������
		cClick.perform();
		//System.out.println(wLinkPrivateOffice2.getText());
		//System.out.println(wLinkPrivateOffice2.getLocation());
		//System.out.println(wLinkMyAdverts.getLocation());*/
		
	}


	
}
