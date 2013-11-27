import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page.mailClientPage.MainPage;
import selenium.DriverConstruct;
import utils.BaseTest;
import utils.enums.FOLDERS;

/**
 * User: dsobol
 */
public class OpenFoldersTest extends BaseTest {

  @Test
  public void Test() {
    MainPage mPage = factory.createPage(MainPage.class);

    for (FOLDERS folder : FOLDERS.values()) {
      Assert.assertTrue(mPage.openFolder(folder).isChooseFolder(folder),
          String.format("Папка %s не была выбрана.", folder.getFullName()));
      Assert.assertTrue((mPage.isFolderLoaded(folder) & mPage.isLinkSortClickabledAndEnabled()),
          String.format("Данные для папки %s не были загружены.", folder.getFullName()));
    }
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    DriverConstruct.stopDriver(DriverConstruct.getDriver());
  }

}
