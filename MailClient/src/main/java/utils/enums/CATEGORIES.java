package utils.enums;

/**
 * User: dsobol
 */
public enum CATEGORIES {

  ALL("All categories");

  private String sFullName;

  private CATEGORIES(String sFullName) {
    this.sFullName = sFullName;
  }

  public String getFullName() {
    return sFullName;
  }

}
