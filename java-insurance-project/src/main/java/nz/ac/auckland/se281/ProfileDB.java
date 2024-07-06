package nz.ac.auckland.se281;

import java.util.List;

public class ProfileDB {

  private String sses;
  private String profileCount;
  private String colon;

  // creating list of profiles
  public ProfileDB(List<Profile> profileList) {

    // setting initial value for colon and value for profileCount
    this.colon = ":";
    this.profileCount = Integer.toString(profileList.size());

    // setting values for sses and colon depending on amount of profiles
    if ((profileList.size()) == 1) {
      sses = "";
    } else if ((profileList.size()) == 0) {
      sses = "s";
      this.colon = ".";
    } else {
      sses = "s";
    }
  }

  // methods for returning values for sses, colon and profile count
  public String getsses() {
    return this.sses;
  }

  public String getcolon() {
    return this.colon;
  }

  public String getprofileCount() {
    return this.profileCount;
  }
}
