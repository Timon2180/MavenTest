package pack_page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pack_utils.ExceptFailTest;
import pack_utils.HM;
import pack_utils.Proper;


public class Page_IrrPrivateOffice extends Page
{
	@FindBy(id="checkAll")
	private WebElement wCheckBoxCheckAll;
	
	@FindBy(id="groupActionDeleteSelected")
	private WebElement wLinkDeleteSelected;
	
	@FindBy(id="groupActionDeactivateSelected")
	private WebElement wLinkDeactivateSelected;
	
	// ������
	@FindBy(xpath="//div[@id='minWidth']//li[@class='all'][1]/div[2]")
	private WebElement wTextAllStatus;
	
	@FindBy(xpath="//div[@id='minWidth']//li[2]/a/div")
	private WebElement wLinkActiveStatus;
	
	@FindBy(xpath="//div[@id='minWidth']//li[3]/a/div")
	private WebElement wLinkNotActiveStatus;
	
	// ���������
	@FindBy(xpath="//div[@class='b-blockInf'][2]//li[@class='all']/div[2]")
	private WebElement wTextAllCategory;
	
	@FindBy(xpath="//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/']/div")
	private WebElement wTextLinkAutoAndMoto;
	
	@FindBy(xpath="//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/passenger/']/div")
	private WebElement wTextLinkEasyAuto;
	
	@FindBy(xpath="//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/passenger/used/']/div")
	private WebElement wTextLinkAutoUsed;
	
	@FindBy(xpath="//div[@class='b-blockInf'][2]//a[@href='/myadverts/otdam-darom/']/div")
	private WebElement wTextLinkTakeFree;
	
	@FindBy(id="logout")
	private WebElement wLinkLogout;
	
	//div class="b-adListTable"
//////////////////////////////////////////////////////////////////////////////////////////////	
	private HM<String, Integer> clStatusAdvert;  // ��� ����� ������
	private String sMas[] = {"��� �������", "��������", "������ � ����������"};
	private Integer iMas[] = new Integer[3];
	
	private HM<String, Integer> clCategoryAdvert; // ��� ����� ���������
	private String sMas2[] = {"��� ���������","���� � ����","����� �����"};
	private Integer iMas2[] = new Integer[3];
	
	public Page_IrrPrivateOffice(WebDriver driver){super(driver);}

	public void GetStatusForLastLogin(HM<String, Integer> clInStatusAdvert, HM<String, Integer> clInStatusAdvertCategory) throws ExceptFailTest
	{
		clStatusAdvert = clInStatusAdvert;
		clCategoryAdvert = clInStatusAdvertCategory;
		wLog.WriteString(1, "�������� ������� ����� ����������� ����������");
		System.out.println("�������� ������� ����� ����������� ����������");
		clStatusAdvert.PrintKeyAndValue(wLog);
		wLog.WriteString(1, "�������� ��������� ����� ����������� ����������");
		System.out.println("�������� ��������� ����� ����������� ����������");
		clCategoryAdvert.PrintKeyAndValue(wLog);
	}
	
	public HM<String, Integer> SendStatus()
	{
		return clStatusAdvert;
	}
	
	public HM<String, Integer> SendCategory()
	{
		return clCategoryAdvert;
	}
	
	// ��� ��������� �������� ���  �������� �������� ��� ��������� ������������� ��������� ��������� ��� ������� � �������� ����� ��������� ���������
	public void CheckCurrentCategory() throws ExceptFailTest
	{
		// ��������� ��� �������� �������� ��� ������� = �������� ��� ���������
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//li[@class='all']/div[2]");
		wLog.WriteString(1, "��������� ��� �������� �������� \"��� �������\" ����� �������� �������� \"��� ���������\"");
		System.out.println("��������� ��� �������� �������� \"��� �������\" ����� �������� �������� \"��� ���������\"");
		int iAllCategory = ParseStringToInt(wTextAllCategory.getText(),"�� ������� ��������� �������� �������� \"��� ��������\" � ����� � ����� \"��������\"");
		int iAllStatus = ParseStringToInt(wTextAllStatus.getText(), "�� ������� ��������� �������� ���������� ���������� � �����");
		wLog.WriteString(1, "�������� �������� \"��� ���������\" � ����� \"���������\" �����: "+iAllCategory);
		System.out.println("�������� �������� \"��� ���������\" � ����� \"���������\" �����: "+iAllCategory);
		wLog.WriteString(1, "�������� �������� \"��� �������\" � ����� \"C������\" �����: "+iAllStatus);
		System.out.println("�������� �������� \"��� �������\" � ����� \"C������\" �����: "+iAllStatus);
		if(iAllCategory != iAllStatus)
		{
			wLog.WriteString(2, "�������� �������� \"��� �������\" � ����� \"������\" �� ����� �������� �������� \"��� ���������\" � ����� \"���������\"");
			throw new ExceptFailTest("�������� �������� \"��� �������\" � ����� \"������\" �� ����� �������� �������� \"��� ���������\" � ����� \"���������\""); 
		}
		else 
		{
			wLog.WriteString(1, "�������� �������� \"��� �������\" � ����� \"������\" ����� �������� �������� \"��� ���������\" � ����� \"���������\"");
			System.out.println("�������� �������� \"��� �������\" � ����� \"������\" ����� �������� �������� \"��� ���������\" � ����� \"���������\"");
		}
		// �������� ��� ���� ���������� ���� �� �������� �������� ��� ��������� ����� ����� �������� ���������  ���������
		if(iAllCategory != 0)
		{
			CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/']/div");
			CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/otdam-darom/']/div");
			int iAuto = ParseStringToInt(wTextLinkAutoAndMoto.getText(),"�� ������� ��������� �������� �������� \"���� � ����\" � ����� � ����� \"��������\"");
			int iTakeFree  = ParseStringToInt(wTextLinkTakeFree.getText(),"�� ������� ��������� �������� �������� \"����� �����\" � ����� � ����� \"��������\"");
			wLog.WriteString(1, "��������� ��� ����� �������� ��������� ������� ��������� " +
					"����� �������� �������� \"��� ���������\" � ����� \"���������\"");
			System.out.println("��������� ��� ����� �������� ��������� ������� ��������� " +
					"����� �������� �������� \"��� ���������\" � ����� \"���������\"");
			wLog.WriteString(1, "�������� �������� \"��� ���������\" � ����� \"���������\" �����: "+iAllCategory);
			System.out.println("�������� �������� \"��� ���������\" � ����� \"���������\" �����: "+iAllCategory);
			wLog.WriteString(1, "����� �������� ��������� \"���� � ����\" � \"����� �����\" �����: "+(iAuto+iTakeFree));
			System.out.println("����� �������� ��������� \"���� � ����\" � \"����� �����\" �����: "+(iAuto+iTakeFree));
			if(iAllCategory == (iAuto+iTakeFree))
			{
				wLog.WriteString(1, "� ����� \"���������\" ����� �������� ��������� ������� ��������� ����� �������� �������� \"��� ���������\"");
				System.out.println("� ����� \"���������\" ����� �������� ��������� ������� ��������� ����� �������� �������� \"��� ���������\"");
				return;
			}
			else
			{
				wLog.WriteString(1, "C���� �������� ��������� ������� ��������� �� ����� �������� �������� \"��� ���������\" � ����� \"���������\"");
				throw new ExceptFailTest("C���� �������� ��������� ������� ��������� �� ����� �������� �������� \"��� ���������\" � ����� \"���������\"");
			}
		}
		
		//  ��������� ��� ���� ���������� ��� ������� ��� ��������� ����� 0 , �� � ��� ������ �� ���������
		if(iAllCategory == 0)
		{
			wLog.WriteString(1, "��������� ��� ���� \"���������\" �� �������� ������ �� ��������� � ������������");
			System.out.println("��������� ��� ���� \"���������\" �� �������� ������ �� ��������� � ������������");
			wLog.WriteString(1, "�������� �������� \"��� ���������\" � ����� \"���������\" ����� 0");
			System.out.println("�������� �������� \"��� ���������\" � ����� \"���������\" ����� 0");
			if(GetCountAllChildrenCategoryFromListCategory() == 0)
			{
				wLog.WriteString(1, "� ����� \"���������\" ���������� ������ � ����������.");
				System.out.println("� ����� \"���������\" ���������� ������ � ����������.");
				Integer iTemp[] = {0,0,0}; // ���� ��� ���������� ������� �� ���� �������� ��������� ���������
				clCategoryAdvert = new HM<String,Integer>(sMas2, iTemp);
				return;
			}
			else 
			{
				wLog.WriteString(2, "�������� �������� \"��� ���������\" � ����� \"���������\" ����� 0, ������ � ����� ������������ ������ � ����������");
				throw new ExceptFailTest("�������� �������� \"��� ���������\" � ����� \"���������\" ����� 0, ������ � ����� ������������ ������ � ����������");
			}
		} 
	}
	
	public void GetCurrentCategory() throws ExceptFailTest
	{
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//li[@class='all']/div[2]");
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/']/div");
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/otdam-darom/']/div");
		iMas2[0] = ParseStringToInt(wTextAllCategory.getText(),"�� ������� ��������� �������� �������� \"��� ��������\" � ����� � ����� \"��������\"");
		iMas2[1] = ParseStringToInt(wTextLinkAutoAndMoto.getText(),"�� ������� ��������� �������� �������� \"���� � ����\" � ����� � ����� \"��������\"");
		iMas2[2] = ParseStringToInt(wTextLinkTakeFree.getText(),"�� ������� ��������� �������� �������� \"����� �����\" � ����� � ����� \"��������\"");
		
		clCategoryAdvert = new HM<String,Integer>(sMas2, iMas2);
		wLog.WriteString(1, "�������� ������� ���������:");
		System.out.println("�������� ������� ���������:");
		clCategoryAdvert.PrintKeyAndValue(wLog);
	}
	
	public void CheckOldAndNewCategory(int nOperation) throws ExceptFailTest
	{
		HM<String, Integer> clOldCategoryAdvert = clCategoryAdvert;
		GetCurrentCategory();
		switch (nOperation)
		{
			case 1:// �����������
				wLog.WriteString(1, "��������� �������� ��������� � ����� \"���������\" ����� �����������");
				System.out.println("��������� �������� ��������� � ����� \"���������\" ����� �����������");
				if(clOldCategoryAdvert.GetValue("��� ���������") != clCategoryAdvert.GetValue("��� ���������"))
				{
					wLog.WriteString(2, "�������� �������� \"��� ���������\" ����� ����������� ���������� �� ����� ������� �������� �������� \"��� ���������\" �� �����������");
					throw new ExceptFailTest("�������� �������� \"��� ���������\" ����� ����������� ���������� �� ����� ������� �������� �������� \"��� ���������\" �� �����������");
				}
				if(clOldCategoryAdvert.GetValue("���� � ����") != clCategoryAdvert.GetValue("���� � ����"))
				{
					wLog.WriteString(2, "�������� �������� \"���� � ����\" ����� ����������� ���������� �� ����� ������� �������� �������� \"���� � ����\" �� �����������");
					throw new ExceptFailTest("�������� �������� \"���� � ����\" ����� ����������� ���������� �� ����� ������� �������� �������� \"���� � ����\" �� �����������");
				}
				if(clOldCategoryAdvert.GetValue("����� �����") != clCategoryAdvert.GetValue("����� �����"))
				{
					wLog.WriteString(2, "�������� �������� \"����� �����\" ����� ����������� ���������� �� ����� ������� �������� �������� \"����� �����\" �� �����������");
					throw new ExceptFailTest("�������� �������� \"����� �����\" ����� ����������� ���������� �� ����� ������� �������� �������� \"����� �����\" �� �����������");
				}
				wLog.WriteString(1, "�������� ��������� � ����� \"��� ���������\" ����� ����������� ���������, �������� ��������");
				System.out.println("�������� ��������� � ����� \"��� ���������\" ����� ����������� ���������, �������� ��������");
				wLog.WriteString(1, "��������� ������������ �������� ��������� ������� � �� �����������. ������� ����.");
				System.out.println("��������� ������������ �������� ��������� ������� � �� �����������. ������� ����.");
				CheckCountAuto();
				break;
			case 2: // ����������
				wLog.WriteString(1, "��������� �������� ��������� � ����� \"���������\" ����� ���������� ����������");
				System.out.println("��������� �������� ��������� � ����� \"���������\" ����� ���������� ����������");
				if((clOldCategoryAdvert.GetValue("��� ���������")+2) != clCategoryAdvert.GetValue("��� ���������"))
				{
					wLog.WriteString(2, "�������� �������� \"��� ���������\" � ����� \"���������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
					throw new ExceptFailTest("�������� �������� \"��� ���������\" � ����� \"���������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
				}
				if((clOldCategoryAdvert.GetValue("���� � ����")+1) != clCategoryAdvert.GetValue("���� � ����"))
				{
					wLog.WriteString(2, "�������� �������� \"���� � ����\" � ����� \"���������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
					throw new ExceptFailTest("�������� �������� \"���� � ����\" � ����� \"���������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
				}
				if((clOldCategoryAdvert.GetValue("����� �����")+1) != clCategoryAdvert.GetValue("����� �����"))
				{
					wLog.WriteString(2, "�������� �������� \"����� �����\" � ����� \"���������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
					throw new ExceptFailTest("�������� �������� \"����� �����\" � ����� \"���������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
				}
				wLog.WriteString(1, "�������� ��������� ���������, ���������� ���������");
				System.out.println("�������� ��������� ���������, ���������� ���������");
				wLog.WriteString(1, "��������� ������������ �������� ��������� ������� � �� �����������. ������� ����.");
				System.out.println("��������� ������������ �������� ��������� ������� � �� �����������. ������� ����.");
				CheckCountAuto();
				break;
		}
		
	}
	
	
	// ��������� �������� ��������� � ����� ������
	public void GetCurrentStatus() throws ExceptFailTest
	{
		CheckElementPresent(1, "//div[@id='minWidth']//li[@class='all'][1]/div[2]");
		CheckElementPresent(1,  "//div[@id='minWidth']//li[2]/a/div");
		CheckElementPresent(1,  "//div[@id='minWidth']//li[3]/a/div");
		
		iMas[0]=ParseStringToInt(wTextAllStatus.getText(), "�� ������� ��������� �������� ���������� ���������� � �����");
		iMas[1]=ParseStringToInt(wLinkActiveStatus.getText(),"�� ������� ��������� �������� ���������� �������� ���������� � �����");
		iMas[2]=ParseStringToInt(wLinkNotActiveStatus.getText(),"�� ������� ��������� �������� ���������� ���������� ���������� � �����");
		
		clStatusAdvert = new HM<String,Integer>(sMas, iMas);
		wLog.WriteString(1, "�������� �������� �������:");
		System.out.println("�������� �������� �������:");
		clStatusAdvert.PrintKeyAndValue(wLog);	
	}
	
	// �������� ��������� ��� ����� ������ 
	public void CheckOldAndNewStatus(int nOperation) throws ExceptFailTest
	{
		HM<String, Integer> clOldStatus = clStatusAdvert;
		GetCurrentStatus();
		switch (nOperation)
		{
			case 1:// �����������
				wLog.WriteString(1, "��������� �������� ��������� � ����� \"������\" ����� �����������");
				System.out.println("��������� �������� ��������� � ����� \"������\" ����� �����������");
				if(clOldStatus.GetValue("��� �������") != clStatusAdvert.GetValue("��� �������"))
				{
					wLog.WriteString(2, "����� �������� �������� \"��� �������\" � ����� \"������\" ����� ����������� ���������� �� ����� ������� ��������");
					throw new ExceptFailTest("����� �������� �������� \"��� �������\" � ����� \"������\" ����� ����������� ���������� �� ����� ������� ��������");
				}
				if(clStatusAdvert.GetValue("��������") !=  0)
				{
					wLog.WriteString(2, "�������� �������� \"��������\" ����� ����������� ���� ���������� �� ����� ����");
					throw new ExceptFailTest("�������� �������� \"��������\" ����� ����������� ���� ���������� �� ����� ����");
				}
				if((clOldStatus.GetValue("��������") + clOldStatus.GetValue("������ � ����������")) != clStatusAdvert.GetValue("������ � ����������"))
				{
					wLog.WriteString(2, "�������� �������� \"������ � ����������\" ����� ����������� ���� ���������� �� ����� �������� �������� \"��������\" �� ��������� ���� ����������");
					throw new ExceptFailTest("�������� �������� \"������ � ����������\" ����� ����������� ���� ���������� �� ����� �������� �������� \"��������\" �� ��������� ���� ����������");
				}
				wLog.WriteString(1, "�������� ��������� ���������, ��� ���������� ��������������");
				System.out.println("�������� ��������� ���������, ��� ���������� ��������������");
				break;
			case 2://��������
				wLog.WriteString(1, "��������� �������� ��������� � ����� \"������\" ����� ��������");
				System.out.println("��������� �������� ��������� � ����� \"������\" ����� ��������");
				if((clStatusAdvert.GetValue("��� �������") != 0) || (clStatusAdvert.GetValue("��������") != 0) || (clStatusAdvert.GetValue("������ � ����������") != 0))
				{
					wLog.WriteString(2, "�������� ��������� � ����� \"������\" �� ����������,����� �������� ���� ����������");
					throw new ExceptFailTest("�������� ��������� � ����� \"������\" �� ����������,����� �������� ���� ����������");
				}
				else
				{
					wLog.WriteString(1, "�������� ��������� � ����� \"������\" ���������, ����� �������� ���� ���������� ����� ����");
					System.out.println("�������� ��������� � ����� \"������\" ���������, ����� �������� ���� ���������� ����� ����");
				}
				break;
			case 3://����������
				wLog.WriteString(1, "��������� �������� ��������� � ����� \"������\" ����� ���������� ����������");
				System.out.println("��������� �������� ��������� � ����� \"������\" ����� ���������� ����������");
				if( (clOldStatus.GetValue("��� �������")+2) != clStatusAdvert.GetValue("��� �������") ) 
				{
					wLog.WriteString(2, "�������� ��������� \"��� �������\" � ����� \"������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
					throw new ExceptFailTest("�������� ��������� \"��� �������\" � ����� \"������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
				}
				if( (clOldStatus.GetValue("��������")+2) != clStatusAdvert.GetValue("��������") )
				{
					wLog.WriteString(2, "�������� �������� \"��������\" � ����� \"������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
					throw new ExceptFailTest("�������� �������� \"��������\" � ����� \"������\" ����� ���������� ���������� �� ����������� �� �������� ���������� ����������� ����������");
				}
				if( clOldStatus.GetValue("������ � ����������") != clStatusAdvert.GetValue("������ � ����������") )
				{
					wLog.WriteString(2, "�������� ��������  \"������ � ����������\" � ����� \"������\" ����� ���������� ���������� � ������� ������� ����������");
					throw new ExceptFailTest("�������� ��������  \"������ � ����������\" � ����� \"������\" ����� ���������� ���������� � ������� ������� ����������");
				}
				wLog.WriteString(1, "�������� ��������� ���������, ���������� ���������");
				System.out.println("�������� ��������� ���������, ���������� ���������");
				break;
		}
	}
	
	// ��� ����� ������ �������� ������������ ��������� � ����� ������ ���������� ������������ ���������� �� �  �������� 
	public void CheckCountAndVisibleAdvert () throws ExceptFailTest // �������� ��� ����� ��������. ��������, ��� �������� ���������� �� ����� ��������� ����� ������������ ���������� ������������ � ������ � �������� �
	{
		Sleep(ParseStringToInt(Proper.GetProperty("timeReloadPage"),"�� ������� ��������� �������� ������� ������������ �������� timeReloadPage ��������� � ������� � �����"));
		driver.get(driver.getCurrentUrl());
		wLog.WriteString(1, "���������, ��� �������� ��������� � ����� \"�������\" �������������" +
				" ���������� ������������ ���������� � ��������������� ��������");
		System.out.println("���������, ��� �������� ��������� � ����� \"�������\" �������������" +
				" ���������� ������������ ���������� � ��������������� ��������");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Long lVisibleAllAdvert = (Long)js.executeScript("return document.getElementsByClassName(\"rowsButton\").length;"); // �������� ���������� ������������ ���������� ��� �������(������� ���������)
		Long lVisibleNonActiveAdvert = (Long)js.executeScript("return document.getElementsByClassName(\"wrButton\").length");  // ���������� ������������ , ���� ������ ����������
		Long lCountAllStatus = (long) ParseStringToInt(wTextAllStatus.getText(), "�� ������� ��������� �������� ���������� ���������� � �����"); // �������� �������� �������� ��� �������
		Long lCountNonActiveAdvert = (long) ParseStringToInt(wLinkNotActiveStatus.getText(), "�� ������� ��������� �������� ���������� ���������� ���������� � �����"); // �������� �������� �������� ����������
		Long lCountActiveAdvert = (long) ParseStringToInt(wLinkActiveStatus.getText(), "�� ������� ��������� �������� ���������� �������� ���������� � �����");
		
		if(lCountAllStatus != lVisibleAllAdvert)
		{
			wLog.WriteString(2, "�������� �������� \"��� �������\" "+lCountAllStatus+" �� ����� ���������� ������������ ���������� "+lVisibleAllAdvert+".");
			throw new ExceptFailTest("�������� �������� \"��� �������\" "+lCountAllStatus+" �� ����� ���������� ������������ ���������� "+lVisibleAllAdvert+".");
		}
		wLog.WriteString(1, "�������� �������� \"��� �������\" "+lCountAllStatus+" ����� ���������� ������������ ���������� "+lVisibleAllAdvert+".");
		System.out.println("�������� �������� \"��� �������\" "+lCountAllStatus+" ����� ���������� ������������ ���������� "+lVisibleAllAdvert+".");
		
		if(lCountActiveAdvert != (lVisibleAllAdvert-lVisibleNonActiveAdvert))
		{
			wLog.WriteString(2, "�������� �������� \"��������\" "+lCountActiveAdvert+" �� ����� ���������� ������������ �������� ���������� "+(lVisibleAllAdvert-lVisibleNonActiveAdvert)+".");
			throw new ExceptFailTest("�������� �������� \"��������\" "+lCountActiveAdvert+" �� ����� ���������� ������������ �������� ���������� "+(lVisibleAllAdvert-lVisibleNonActiveAdvert)+".");
		}
		wLog.WriteString(1, "�������� �������� \"��������\" "+lCountActiveAdvert+" ����� ���������� ������������ �������� ���������� "+(lVisibleAllAdvert-lVisibleNonActiveAdvert)+".");
		System.out.println("�������� �������� \"��������\" "+lCountActiveAdvert+" ����� ���������� ������������ �������� ���������� "+(lVisibleAllAdvert-lVisibleNonActiveAdvert)+".");

		if(lVisibleNonActiveAdvert != lCountNonActiveAdvert)
		{
			wLog.WriteString(2, "�������� �������� \"������ � ����������\" "+lCountNonActiveAdvert+"�� ����� ���������� ������������ ������ � ���������� ���������� "+lVisibleNonActiveAdvert+".");
			throw new ExceptFailTest("�������� �������� \"������ � ����������\" "+lCountNonActiveAdvert+"�� ����� ���������� ������������ ������ � ���������� ���������� "+lVisibleNonActiveAdvert+".");
		}
		wLog.WriteString(1, "�������� �������� \"������ � ����������\" "+lCountNonActiveAdvert+" ����� ���������� ������������ ������ � ���������� ���������� "+lVisibleNonActiveAdvert+".");
		System.out.println("�������� �������� \"������ � ����������\" "+lCountNonActiveAdvert+" ����� ���������� ������������ ������ � ���������� ���������� "+lVisibleNonActiveAdvert+".");
	}
	
	@Override
	public void OpenPage(){}
	
	// �������� ���� ����������
	public void DeleteAllAdvert() throws ExceptFailTest
	{
		wLog.WriteString(1, "������� ��� ����������");
		System.out.println("������� ��� ����������");
		CheckElementPresent(2, "checkAll");
		wCheckBoxCheckAll.click();
		CheckElementPresent(2, "groupActionDeleteSelected");
		wLinkDeleteSelected.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	// ����������� ���������� ����
	public void DeactivateAllAdvert() throws ExceptFailTest
	{
		wLog.WriteString(1, "������������ ��� ����������");
		System.out.println("������������ ��� ����������");
		CheckElementPresent(2, "checkAll");
		wCheckBoxCheckAll.click();
		CheckElementPresent(2,"groupActionDeactivateSelected");
		wLinkDeactivateSelected.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	// ������
	public void LogOutFromIrr() throws ExceptFailTest
		{
			wLog.WriteString(1, "��������������");
			System.out.println("��������������");
			CheckElementPresent(2, "logout");
			wLinkLogout.click();
		}
	
	// ��������� �������� ���������� �������� ������� � ����������� � ����
	private void CheckCountAuto() throws ExceptFailTest  
	{
		wLog.WriteString(1, "��������� ��� �������� ��������  ������� \"���� � ����\" ����� ��������� ���" +
				" �������� \"�������� ����������\" � \"���������� � ��������\" ");
		System.out.println("��������� ��� �������� ��������  ������� \"���� � ����\" ����� ��������� ���" +
				" �������� \"�������� ����������\" � \"���������� � ��������\" ");
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/']/div");
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/passenger/']/div");
		CheckElementPresent(1,"//div[@class='b-blockInf'][2]//a[@href='/myadverts/cars/passenger/used/']/div");
		int iAuto = ParseStringToInt(wTextLinkAutoAndMoto.getText(),"�� ������� ��������� �������� �������� \"���� � ����\" � ����� � ����� \"��������\"");
		int iEasyAuto = ParseStringToInt(wTextLinkEasyAuto.getText(),"�� ������� ��������� �������� �������� \"�������� ����������\" � ����� � ����� \"��������\"");
		int iAutoUsed = ParseStringToInt(wTextLinkAutoUsed.getText(),"�� ������� ��������� �������� �������� \"���������� � ��������\" � ����� � ����� \"��������\""); 	
		wLog.WriteString(1, "������� \"���� � ����\" �����: "+ iAuto);
		System.out.println("������� \"���� � ����\" �����: "+ iAuto);
		wLog.WriteString(1, "������� \"�������� ����������\" �����: "+ iEasyAuto);
		System.out.println("������� \"�������� ����������\" �����: "+ iEasyAuto);
		wLog.WriteString(1, "������� \"���������� � ��������\" �����: "+ iEasyAuto);
		System.out.println("������� \"���������� � ��������\" �����: "+ iEasyAuto);
		if((iAuto != iEasyAuto) || (iEasyAuto != iAutoUsed) || (iAutoUsed != iAuto))
		{
			wLog.WriteString(2, "�������� ��������  ������� \"���� � ����\" �� ����� ��������� ��� �������� \"�������� ����������\" � \"���������� � ��������\" ");
			throw new ExceptFailTest("�������� ��������  ������� \"���� � ����\" �� ����� ��������� ��� �������� \"�������� ����������\" � \"���������� � ��������\" ");
		}
		wLog.WriteString(1, "�������� �������� � ������� \"���� � ����\" ����� ��������� ��� �������� \"�������� ����������\" � \"���������� � ��������\"");
		System.out.println("�������� �������� � ������� \"���� � ����\" ����� ��������� ��� �������� \"�������� ����������\" � \"���������� � ��������\"");
	}
	
	// ��� ��������� -  ���������� ���� ��������� � ������������
	private Long GetCountAllChildrenCategoryFromListCategory() throws ExceptFailTest
	{
		wLog.WriteString(1, "�������� ���������� �������(������) � ���������� � ������������� � ����� \"���������\"");
		System.out.println("�������� ���������� �������(������) � ���������� � ������������� � ����� \"���������\"");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Long lLinkAllAdvertCategory = (Long)js.executeScript(" var j = document.getElementsByClassName(\"b-blockInf\"); return j[1].getElementsByTagName(\"a\").length ");
		wLog.WriteString(1, "���������� ������� � ���������� � ������������� � ����� \"���������\" �����: "+lLinkAllAdvertCategory);
		System.out.println("���������� ������� � ���������� � ������������� � ����� \"���������\" �����: "+lLinkAllAdvertCategory);
		return lLinkAllAdvertCategory;
	}

	
	private int ParseStringToInt(String sCount, String sMessage) throws ExceptFailTest
	{
		try
		{
			int iTemp = Integer.parseInt(sCount);
			return iTemp;
		}
		catch(NumberFormatException exc)
		{
			wLog.WriteString(1, sMessage);
			throw new ExceptFailTest(sMessage);
		}
	}

}
