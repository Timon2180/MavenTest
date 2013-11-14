import org.testng.annotations.Test;
import org.testng.Assert;
import page.MainPage;
import utils.enums.FOLDERS;

/**
 * User: dsobol
 */
public class OpenFoldersTest {


  @Test
  public void Test() {
    MainPage mPage = new MainPage();
    Assert.assertTrue(mPage.openFolder(FOLDERS.AJAX).isChooseFolder(FOLDERS.AJAX), "Папка AJAX не открылась");

  }

}
