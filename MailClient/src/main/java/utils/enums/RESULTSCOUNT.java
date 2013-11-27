package utils.enums;

/**
 * User: dsobol
 */
public enum RESULTSCOUNT {

  ALL("All results per page"),
  ZA("4 results per page"),
  YEAR_HIGH("8 results per page"),
  YEAR_DOWN("12 results per page");

  // and TODO

  private String sFullName;

  private RESULTSCOUNT(String sFullName) {
    this.sFullName = sFullName;
  }

  public String getFullName() {
    return sFullName;
  }


}
