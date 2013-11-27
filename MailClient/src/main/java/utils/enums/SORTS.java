package utils.enums;

import sun.misc.Sort;

/**
 * User: dsobol
 */
public enum SORTS {

  AZ("Sort by name (A-Z)"),
  ZA("Sort by name (Z-A)"),
  YEAR_HIGH("Sort by year (0-9)"),
  YEAR_DOWN("Sort by year (9-0)");

  // and TODO

  private String sFullName;

  private SORTS(String sFullName) {
    this.sFullName = sFullName;
  }

  public String getFullName() {
    return sFullName;
  }

}
