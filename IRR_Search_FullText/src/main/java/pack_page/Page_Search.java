package pack_page;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pack_utils.ExceptFailTest;
import pack_utils.Proper;

public class Page_Search extends Page
{
	@FindBy(xpath="//div[@data-item-name='make']//div[@class='choseMark']//span")
	private WebElement wFieldMake;
	
	// массив ссылок для объявлений
	private WebElement wAdvert[];
	private ArrayList<String> sListLinks = new ArrayList<String>(); // лист ссылок на найденные объявления
	Page_Advert pageAdvert; //  страница объявлений
	private String sUrlFirstAdvert; // ссылка на первое объявление в листинге
	
	private WebElement wLinkBlockSuggest[]; // массив ссылок блока  "Вам также может быть интересно"
	
	ArrayList<String> listNameSuggestBlock = new ArrayList<String>(); // название саджестов в блоке Вам так же будет интересно
	ArrayList<String> listLinkSuggestBlock = new ArrayList<String>(); // ссылки саджестов в блоке Вам так же будет интересно
	
	private WebElement wLinkMainCategories[]; // ссылки на главные категории на странице с результатами поиска 
	String s = "//div[@class='b-menuCat']/ul/li/a";
	int nCountCategory = 0; //количество ссылок на главные категории на странице с результатами поиска 
	
	public Page_Search(WebDriver driver){super(driver);}
	public void OpenPage(String sUrl){}
	
	// получаем ссылки на объявления из найденных
	public ArrayList<String> GetAdverts() throws ExceptFailTest
	{
		Sleep(500);
		wAdvert = GetAllWebElements("//table[@class='adListTable']//td[@class='tdTxt']/div[@class='h3']/a");
		//print(wAdvert.length);
		for(int i=0; i<wAdvert.length; i++)
		{
			sListLinks.add(wAdvert[i].getAttribute("href"));
		}
		return sListLinks;
	}
	
	// получение и проверка объявления по одному слову
	public void CheckAdvertByOneWords(String sFindString) throws ExceptFailTest
	{
		String sFind[];  // массив искомых слов
		boolean bFlag = false;
		int i = 0;
		Iterator<String> it = sListLinks.iterator();
		while(it.hasNext())
		{
			/////////
			//if(i==2)
				//break;
			////////////
			print("\r\nОбъявление №" + i++);
			pageAdvert = PageFactory.initElements(driver, Page_Advert.class);
			String sUrl = it.next();
			driver.get(sUrl);
			Sleep(500);
			String sTitle = pageAdvert.GetAdvertTitle(sUrl);
			print("\"" + sTitle + "\"");
			String sText = pageAdvert.GetAdvertText();
			print("\"" + sText + "\"");
			bFlag = false;
			sFind = GetStringFind(sFindString);
			
			print("Ищем в объявлении любое слово из - " + sFindString);
			print("Ищем в заголовке".toUpperCase());
			bFlag = CheckString(sTitle, sFind, "заголовке");
			// если нашли то берем следующее объявление
			if(bFlag)
				continue;
			
			// иначе проверяем текст объявления, если его нету то валим тест
			if(sText.equals(""))
			{
				print("В объявлении отсутсвует текст");
				print("Любое из искомых слов - " + sFindString + " отсутствует в объявлении. Тест провален");
				throw new ExceptFailTest("Любое из искомых слов - " + sFindString + " отсутствует в объявлении. Тест провален");
			}
			// если текст есть то ищем в нем
			print("Ищем в тексте объявления".toUpperCase());
			bFlag = CheckString(sText, sFind, "тексте объявления");		
			if(bFlag == false)
			{
				print("Любое из искомых слов - " + sFindString + " отсутствует в объявлении. Тест провален");
				throw new ExceptFailTest("Любое из искомых слов - " + sFindString + " отсутствует в объявлении. Тест провален");
			}
			
		}	
	}
	
	// Получение и проверка объявления по двум словам
	public void CheckAdvertByTwoWords(String sFindString) throws ExceptFailTest
	{
		String sFind[];  // массив искомых слов
	
		int i = 0;
		
		
		Iterator<String> it = sListLinks.iterator();
		while(it.hasNext())
		{
			/////////
			//if(i==2)
				//break;
			////////////
			print("\r\nОбъявление №" + i++);
			pageAdvert = PageFactory.initElements(driver, Page_Advert.class);
			String sUrl = it.next();
			driver.get(sUrl);
			Sleep(500);
			String sTitle = pageAdvert.GetAdvertTitle(sUrl);
			print("\"" + sTitle + "\"");
			String sText = pageAdvert.GetAdvertText();
			print("\"" + sText + "\"");
			
			sFind = GetStringFind(sFindString);
	
			print("Ищем в объявлении два слова - " + sFindString);
			for(int j=0; j<sFind.length; j++)
			{
				print("Ищем слово - \"" + sFind[j] + "\" в заголовке");
				if(sTitle.toLowerCase().indexOf(sFind[j].toLowerCase()) != -1)
				{
					print("Слово - \"" + sFind[j] + "\" найдено в заголовке");
					continue;
				}
				else
				{
					print("Ищем слово - \"" + sFind[j] + "\" в тексте объявления");
					if(sText.toLowerCase().indexOf(sFind[j].toLowerCase()) != -1)
					{
						print("Слово - \"" + sFind[j] + "\" найдено в тексте объявления");
					}
					else
					{
						print("Слово - \"" + sFind[j] + "\" не найдено не в заголовке, не в тексте объявления");
						throw new ExceptFailTest("Слово - \"" + sFind[j] + "\" не найдено не в заголовке, не в тексте объявления");
					}
				}
			}	
		}	
	}
	
	// Получение и проверка объявления по кастому марка
	public void CheckAdvertByMake(String sFindMake) throws ExceptFailTest
	{
		int i = 0;
		Iterator<String> it = sListLinks.iterator();
		String sMake;
		
		while(it.hasNext())
		{
			/////////
			//if(i==2)
				//break;
			////////////
			print("\r\nОбъявление №" + i++);
			pageAdvert = PageFactory.initElements(driver, Page_Advert.class);
			String sUrl = it.next();
			driver.get(sUrl);
			Sleep(500);
			sMake = pageAdvert.GetCustomMake(sUrl);
			print("Сравниваем значение полученного из объявления кастома марки со значение саджеста");
			if(sMake.toLowerCase().equals(sFindMake.toLowerCase()))
			{
				print("Значение марки в кастоме равное - \"" + sMake + "\" совпало с значением саджеста равным - \"" + sFindMake + "\"");
			}
			else
			{
				print("Значение марки в кастоме равное - \"" + sMake + "\" не совпало с значением саджеста равным - \"" + sFindMake + "\"");
				throw new ExceptFailTest("Значение марки в кастоме равное - \"" + sMake + "\" не совпало с значением саджеста равным - \"" + sFindMake + "\"");
			}
		}
		
	}
	
	// сохраняем ссылку на первое найденное объявление
	public String SaveLinkFirstAdvert()
	{
		print("Запоминаем урл первого объявления в найденных результатах");
		sUrlFirstAdvert = sListLinks.get(0);
		print("Ссылка первого найденного объявления = " + sUrlFirstAdvert);
		return sUrlFirstAdvert;
	}
	
	// поиск первого объявления из одного листинга результатов в другом листинге результатов( когда ищем по синонимам (бмв и бумер) к примеру) 
	public void LikeLinkAdvert(String sFirstUrl) throws ExceptFailTest
	{
		boolean bFlag = false;
		print("Ищем в новых результатах поиска, объявление из предыдущего поиска");
		Iterator<String> it = sListLinks.iterator();
		while(it.hasNext())
		{
			if(sFirstUrl.equals(it.next()))
			{
				bFlag = true;
				break;
			}
		}
		if(bFlag)
		{
			print("Объявление найдено. Корректно");
		}
		else
		{
			print("Объявление не найдено, хотя поиск производился по слову синониму");
			throw new ExceptFailTest("Объявление не найдено, хотя поиск производился по слову синониму");
		}
	}
	
	// получение значения фильтра марка и сравнения его с саджестом
	public void GetMakeInFilterAndCompareWithFindWord(String sFindString) throws ExceptFailTest
	{
		print("Проверяем отображается ли марка указанная для саджестов - \"" + sFindString + "\" в фильтрах");
		CheckElementPresent(1, "//div[@data-item-name='make']//div[@class='choseMark']//span");
		if(Proper.GetProperty("SuggestBMW").toLowerCase().equals(wFieldMake.getText().toLowerCase()))
		{
			print("Марка отображается в фильтрах ее значение - \"" + wFieldMake.getText() + "\" совпало с искомым саджестом - \"" + sFindString + "\"");
		}
		else
		{
			print("Марка не отображается в фильтрах или ее значение - \"" + wFieldMake.getText() + "\" не совпало с искомым саджестом - \"" + sFindString + "\"");
			throw new ExceptFailTest("Марка не отображается в фильтрах или ее значение - \"" + wFieldMake.getText() + "\" не совпало с искомым саджестом - \"" + sFindString + "\"");
		}
	}
	
	// получение названий и ссылок на саджесты в блоке "Вам так же будет интересно"
	public void GetLinksBlockIntresting() throws ExceptFailTest, UnsupportedEncodingException
	{
		print("Получем название и ссылки в блоке саджестов \"Вам также может быть интересно\"");
		wLinkBlockSuggest = GetAllWebElements("//div[@class='additional_rubrics']//a");
		for(int i=0; i<wLinkBlockSuggest.length; i++)
		{
			listNameSuggestBlock.add(wLinkBlockSuggest[i].getText().toLowerCase().replaceAll(",", ""));
			listLinkSuggestBlock.add(URLDecoder.decode(wLinkBlockSuggest[i].getAttribute("href"), "utf-8").toLowerCase());
		}
		
		print("\r\nСписок названий саджестов в блоке \"Вам так же будет интересно\" получен");
		for(int i=0; i<listNameSuggestBlock.size(); i++)
		{
			print(listNameSuggestBlock.get(i));
		}
		
		print("\r\nСписок ссылок саджестов в блоке \"Вам так же будет интересно\" получен");
		for(int i=0; i<listLinkSuggestBlock.size(); i++)
		{
			print(listLinkSuggestBlock.get(i));
		}
		
	}
	
	//сравнение ссылок и названий саджестов с главной с саджестами в блоке Вам так же будет интересно
	public void CompareSuggestInMainWithSuggestInBlock(ArrayList<String> listNames, ArrayList<String> listLinks) throws ExceptFailTest
	{	
		print("\r\nПроверяем совпадение названий и ссылок в саджестах на главной и в блоке \"Вам так же будет интересно\"");
		
		if(listNames.containsAll(listNameSuggestBlock))
			print("Списки названий саджестов одинаковы");
		else
		{
			print("Списки названий саджестов не одинаковы");
			throw new ExceptFailTest("Списки названий саджестов не одинаковы");
		}
		
		if(listLinks.containsAll(listLinkSuggestBlock))
			print("Списки ссылок саджестов одинаковы");
		else
		{
			print("Списки ссылок саджестов не одинаковы");
			throw new ExceptFailTest("Списки ссылок саджестов не одинаковы");
		}
	}
	
	//проверка отсутсвия блока Вам это так же интересно
	public void CheckPresentBlockInterest(int nOperation) throws ExceptFailTest
	{
		switch(nOperation)
		{
			case 1:
				print("Проверяем отсутствие блока \"Вам так же будет интересно\"");
				if(!CheckElement("//div[@class='additional_rubrics']", "\"Блок\" \"Вам так же будет интересно\""))
					print("Блок  \"Вам так же будет интересно\" отсутствует. Корректно.");
				else
				{
					print("Блок  \"Вам так же будет интересно\" присутсвует. Но его не должно быть так как мы перешли по саджесту в конечную рубрику.");
					throw new ExceptFailTest("Блок  \"Вам так же будет интересно\" присутсвует. Но его не должно быть так как мы перешли по саджесту в конечную рубрику.");
				}
				break;
				
			case 2:
				print("Проверяем присутствие блока \"Вам так же будет интересно\"");
				if(CheckElement("//div[@class='additional_rubrics']", "\"Блок\" \"Вам так же будет интересно\""))
					print("Блок  \"Вам так же будет интересно\" присутствует. Корректно.");
				else
				{
					print("Блок  \"Вам так же будет интересно\" отсутствует. Но он должен быть.");
					throw new ExceptFailTest("Блок  \"Вам так же будет интересно\" отсутствует. Но он должен быть.");
				}
				break;
		}
	}
	
	// получения ссылок на главные категории на странице с результатами поиска
	public void GetLinksMainCategoryInSeachPage() throws ExceptFailTest
	{
		print("Получаем ссылки на главные категории на странице с результами поиска");
		try
		{
			wLinkMainCategories = GetAllWebElements(s);
		}
		catch(ExceptFailTest exc)
		{
			print("Нет одной категории в результатах поиска");
			throw new ExceptFailTest("Нет одной категории в результатах поиска");
		}
		nCountCategory = wLinkMainCategories.length;	
	}
	
	// проверяем что в результатах только одна категория главная 
	public void CheckNamesAndCountMainCategoriesInSearchPage(String sNameMainCategory, int nOperation) throws ExceptFailTest
	{
		switch(nOperation)
		{
		case 1:
			print("Проверяем что на странице результатов поиска только одна главная категория - " + sNameMainCategory);
			if(nCountCategory == 1)
			{
				if(wLinkMainCategories[0].getText().equals(sNameMainCategory))
					print("Количество главных категорий на странице с результатами поиска равно один и название категории - \"" +
							wLinkMainCategories[0].getText() + "\" совпало с ожидаемым названием \"" + sNameMainCategory + "\"");
				else
				{
					print("Количество главных категорий на странице с результатами поиска равно один но название категории - \"" +
							wLinkMainCategories[0].getText() + "\" не совпало с ожидаемым названием \"" + sNameMainCategory + "\"");
					throw new ExceptFailTest("Количество главных категорий на странице с результатами поиска равно один но название категории - \"" +
							wLinkMainCategories[0].getText() + "\" не совпало с ожидаемым названием \"" + sNameMainCategory + "\"");
				}
			}
			else
			{
				print("Количество главных категорий на странице с результатами поиска не равно одной ");
				print("На странице отображаются категории:");
				for(int i=0; i<wLinkMainCategories.length; i++)
					print(wLinkMainCategories[i].getText());
				throw new ExceptFailTest("Количество главных категорий на странице с результатами поиска не равно одной ");
			}
			break;
			
		case 2:
			print("Проверяем что на странице результатов поиска не одна главная категории " + sNameMainCategory);
			if(nCountCategory != 1)
			{
				print("Количество главных категорий на странице с результатами поиска не равно одной ");
				print("На странице отображаются категории:");
				for(int i=0; i<wLinkMainCategories.length; i++)
					print(wLinkMainCategories[i].getText());
				print("Корректно");
			}
			else
			{
				print("Количество главных категорий на странице с результатами поиска равно одной - \"" + wLinkMainCategories[0].getText() +
						" но их должно быть больше");
				throw new ExceptFailTest("Количество главных категорий на странице с результатами поиска равно одной - \"" + wLinkMainCategories[0].getText() +
						" но их должно быть больше");
			}
			break;
		}
	}
	
	//сравнение листингов(ссылки на объявления) при поиске
	public void CompareListResult(ArrayList<String> listFirst, ArrayList<String> listSecond) throws ExceptFailTest
	{
		print("\r\nПроверяем совпадение листингов результатов поиска");
		
		if(listFirst.containsAll(listSecond))
			print("Листинги совпали");
		else
		{
			print("Листинги не совпали");
			throw new ExceptFailTest("Листинги не совпали");
		}
	}
	
	
	// из строки массив
	private String[] GetStringFind(String s)
	{
		String sTemp[] = s.split(",");
		return sTemp;
	}
	
	// проверка есть ли текст
	private boolean CheckString(String sCurrent, String sFind[], String sWhere)
	{
		boolean bFlag = false;
		
		for(String sTemp : sFind)
		{
			print("Ищем слово - " + sTemp);
			if(sCurrent.toLowerCase().indexOf(sTemp.toLowerCase()) != -1)
			{
				print("Слово - \"" + sTemp + "\" найдено в " + sWhere);
				bFlag = true;
				break;
			}
			else
				print("Слово - \"" + sTemp + "\" не найдено в " + sWhere);
		}
		
		return bFlag;
	}
	
	
}