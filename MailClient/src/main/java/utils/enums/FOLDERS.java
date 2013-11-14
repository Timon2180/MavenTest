package utils.enums;

/**
 * User: dsobol
 */
public enum FOLDERS {
  AJAX("AJAX"),
  Announcements("Announcements"),
  OpenAccess("OpenAccess ORM"),
  Silverlight("Silverlight"),
  WinForms("WinForms"),
  WPF("WPF");

  private String sFullName;

  private FOLDERS(String sFullName) {
    this.sFullName = sFullName;
  }

  public String getFullName() {
    return sFullName;
  }

}
