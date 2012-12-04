package pack_test;
import pack_page.Page_IrrMain;
import pack_page.Page_IrrPrivateOffice;
import pack_page.Page_LoginStargate;
import pack_page.Page_Stargate;
import pack_utils.ExceptFailTest;
import pack_utils.HM;
import pack_utils.Proper;
import pack_utils.WriterLog;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCount extends TestConstruct
{

	Page_LoginStargate pageLoginStargate = PageFactory.initElements(GetWebDriver(), Page_LoginStargate.class);
	Page_Stargate pageStargate;
	Page_IrrMain pageIrr = PageFactory.initElements(GetWebDriver(), Page_IrrMain.class);
	Page_IrrPrivateOffice pageIrrPrOf;
	HM<String, Integer> clStatusAdvert; //������ ������
	HM<String, Integer> clStatusAdvertCategory; //������ ���������
	WriterLog wLog;// ��� �����
	
	@BeforeTest
	public void befTest() throws ExceptFailTest
	{
		System.out.println("Start @BeforeTest");
		wLog = new WriterLog();
		wLog.SetUpWriterLog("src\\Log_Result.html");
		lthe.GetWritterLog(wLog);
		System.out.println("End @BeforeTest");
	};
	
	@AfterTest
	public void aftTest() throws ExceptFailTest
	{
		System.out.println("Start @AfterTest");
		System.out.println("End @AfterTest");
	}
	
	@Test
	public void TestStart() throws ExceptFailTest
	{
		
		System.out.println("Start @Test");
		try
		{
			pageLoginStargate.GetWriterLog(wLog); // �������� ���
			pageLoginStargate.OpenPage();
			pageLoginStargate.CheckElements();
			pageLoginStargate.TypeLoginPassword();
			pageStargate = pageLoginStargate.EnterStargate();
			pageStargate.GetWriterLog(wLog);
			
			if(Proper.GetProperty("typeAdvert").equals("premium"))
				pageStargate.OpenFormCreatePremiumAuto();
			else
				pageStargate.OpenFormCreateAdvertAuto();
			pageStargate.InputDataAuto();
			
			if(Proper.GetProperty("typeAdvert").equals("premium"))
				pageStargate.OpenFormCreatePremiumFree();
			else
				pageStargate.OpenFormCreateAdvertFree();
			pageStargate.InputDataFree();
			
			pageIrr.GetWriterLog(wLog);
			pageIrr.OpenPage();
			pageIrr.OpenFormAuthorization();
			pageIrrPrOf = pageIrr.LoginOn();
			pageIrrPrOf.GetWriterLog(wLog);
	
			
			pageIrrPrOf.CheckCountAndVisibleAdvert();
			pageIrrPrOf.CheckCurrentCategory();
			pageIrrPrOf.GetCurrentStatus();
			pageIrrPrOf.GetCurrentCategory();
			pageIrrPrOf.DeactivateAllAdvert();
			pageIrrPrOf.CheckCountAndVisibleAdvert();
			pageIrrPrOf.CheckCurrentCategory();
			pageIrrPrOf.CheckOldAndNewStatus(1);
			pageIrrPrOf.CheckOldAndNewCategory(1);
			pageIrrPrOf.DeleteAllAdvert();
			pageIrrPrOf.CheckCountAndVisibleAdvert();
			pageIrrPrOf.CheckCurrentCategory();
			pageIrrPrOf.CheckOldAndNewStatus(2);
		
			clStatusAdvert = pageIrrPrOf.SendStatus();
			clStatusAdvertCategory = pageIrrPrOf.SendCategory();
			pageIrrPrOf.LogOutFromIrr();
			
			
			pageLoginStargate.OpenPage();
			pageLoginStargate.CheckElements();
			pageLoginStargate.TypeLoginPassword();
			pageStargate = pageLoginStargate.EnterStargate();
			pageStargate.GetWriterLog(wLog);
			
			if(Proper.GetProperty("typeAdvert").equals("premium"))
				pageStargate.OpenFormCreatePremiumAuto();
			else
				pageStargate.OpenFormCreateAdvertAuto();
			pageStargate.InputDataAuto();
			
			if(Proper.GetProperty("typeAdvert").equals("premium"))
				pageStargate.OpenFormCreatePremiumFree();
			else
				pageStargate.OpenFormCreateAdvertFree();
			pageStargate.InputDataFree();
			
			pageIrr.OpenPage();
			pageIrr.OpenFormAuthorization();
			pageIrrPrOf = pageIrr.LoginOn();
			pageIrrPrOf.GetWriterLog(wLog);
			
			pageIrrPrOf.GetStatusForLastLogin(clStatusAdvert, clStatusAdvertCategory);
			
			pageIrrPrOf.CheckCountAndVisibleAdvert();
			pageIrrPrOf.CheckCurrentCategory();
			
			pageIrrPrOf.CheckOldAndNewStatus(3);  //������� � ���������� ���������� ����������� ����������
			pageIrrPrOf.CheckOldAndNewCategory(2);
			wLog.WriteString(1, "���� �������� �������");
		}
		finally
		{
			wLog.CloseFile();
			driver.quit();
		}
		System.out.println("End @Test");
	}
	
}
