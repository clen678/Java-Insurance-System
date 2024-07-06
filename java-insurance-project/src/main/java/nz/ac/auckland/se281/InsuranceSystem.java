package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private List<Profile> profileList = new ArrayList<>();
  private String currentProfileName = null;
  private Profile currentProfile = null;
  private Home homePolicy;
  private String policiesString;
  private Life lifePolicy;
  private Car carPolicy;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    // creating instance of ProfileDB "prof"
    ProfileDB prof = new ProfileDB(profileList);

    // printing database header
    MessageCli.PRINT_DB_POLICY_COUNT.printMessage(
        prof.getprofileCount(), prof.getsses(), prof.getcolon());

    // printing database through profileList
    for (int i = 0; i < profileList.size(); i++) {
      Profile selectedProfile = profileList.get(i);

      // setting value for policiesString depending on amount of policies
      if (profileList.get(i).getPolicyCount() == 1) {
        policiesString = "y";
      } else {
        policiesString = "ies";
      }

      // printing "profileNumber: userName, age" for every profile in profileList and
      // printing "***" to the start of the slected currentProfile
      if (selectedProfile.getUserName().equals(currentProfileName)) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** " + Integer.toString(i + 1),
            "",
            selectedProfile.getUserName(),
            selectedProfile.getAge(),
            Integer.toString(selectedProfile.getPolicyCount()),
            policiesString,
            selectedProfile.getTotalPrice());
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            Integer.toString(i + 1),
            "",
            selectedProfile.getUserName(),
            selectedProfile.getAge(),
            Integer.toString(selectedProfile.getPolicyCount()),
            policiesString,
            selectedProfile.getTotalPrice());
      }

      // printing policies for each profile
      selectedProfile.printPolicies();
    }
  }

  public void createNewProfile(String userName, String age) {

    // checking if profile is already loaded, if so return error message
    if (currentProfileName != null || currentProfile != null) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(currentProfileName);
      return;
    }

    // formatting string for userName
    int nameLength = userName.length();
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // checking for duplicate names in profileList
    int profileCount = profileList.size();
    for (int i = 0; i < profileCount; i++) {
      if (userName.equals(profileList.get(i).getUserName())) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        return;
      }
    }

    // checking if age is valid and not a negative number or null
    if (age == null || age.isEmpty() || Integer.parseInt(age) < 0) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return;
    }

    // checking that name is more or equal to 3 characters otherwise return error
    // message
    if (nameLength >= 3) {
      Profile newProfile = new Profile(userName, age);
      profileList.add(newProfile);
      MessageCli.PROFILE_CREATED.printMessage(userName, age);
    } else {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
    }
  }

  public void loadProfile(String userName) {
    int profileCount = profileList.size();

    // formatting string for userName user input
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // looping through profileList to find matching userName with boolean
    boolean found = false;
    for (int i = 0; i < profileCount; i++) {
      if (userName.equals(profileList.get(i).getUserName())) {
        MessageCli.PROFILE_LOADED.printMessage(userName);

        // getting currentProfile if found
        found = true;
        currentProfileName = profileList.get(i).getUserName();
        currentProfile = profileList.get(i);
        break;
      }
    }

    // printing profile not found message if no profile found
    if (found == false) {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    }
  }

  public void unloadProfile() {

    // if no profile is loaded print message otherwise unload profile
    if (currentProfileName == null || currentProfile == null) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    } else {
      MessageCli.PROFILE_UNLOADED.printMessage(currentProfileName);
      currentProfileName = null;
      currentProfile = null;
    }
  }

  public void deleteProfile(String userName) {
    int profileCount = profileList.size();
    boolean found = false;

    // formatting string for userName user input
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // if profile is loaded print error message otherwise delete profile if profile exists in
    // profileList
    if (userName.equals(currentProfileName)) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
    } else {

      // looping through profileList to find matching userName to delete, if none found, print error message
      for (int i = 0; i < profileCount; i++) {
        if (userName.equals(profileList.get(i).getUserName())) {
          profileList.remove(i);
          MessageCli.PROFILE_DELETED.printMessage(userName);
          found = true;
          return;
        }
      }
      if (found == false) {
        MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
      }
    }
  }

  public void createPolicy(PolicyType type, String[] options) {
    int sumInsured = Integer.parseInt(options[0]);

    // returning error message when no profile is loaded
    if (currentProfileName == null || currentProfile == null) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    // CREATING HOME POLICY
    if (type == PolicyType.HOME) {

      // setting value for rental as boolean
      boolean bolRental = true;
      if (options[2].equalsIgnoreCase("yes") || options[2].equalsIgnoreCase("y")) {
        bolRental = true;
      } else if (options[2].equalsIgnoreCase("no") || options[2].equalsIgnoreCase("n")) {
        bolRental = false;
      }

      // creating new home policy using home constructor and adding new policy to
      // policiesList
      homePolicy = new Home(sumInsured, options[1], bolRental, options);
      currentProfile.addPolicy(homePolicy);
      MessageCli.NEW_POLICY_CREATED.printMessage(type.name().toLowerCase(), currentProfileName);
    }

    // CREATING LIFE POLICY
    if (type == PolicyType.LIFE) {

      // if lifePolicyCount > 1 then return error message
      if (currentProfile.getLifePolicyCount() > 1) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(currentProfileName);
        return;
      }

      // getting age of current profile
      int intAge = Integer.parseInt(currentProfile.getAge());

      // return error message if over age limit for life policy
      if (intAge > 100) {
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(currentProfileName);
        return;
      }

      // creating new home policy using home constructor and adding new policy to
      // policiesList
      lifePolicy = new Life(sumInsured, options, intAge);
      currentProfile.addPolicy(lifePolicy);
      MessageCli.NEW_POLICY_CREATED.printMessage(type.name().toLowerCase(), currentProfileName);
    }

    // CREATING CAR POLCIY
    if (type == PolicyType.CAR) {

      // getting age of current profile
      int intAge = Integer.parseInt(currentProfile.getAge());

      // setting value for breakdown as boolean
      boolean bolBreakdown = true;
      if (options[3].equalsIgnoreCase("yes") || options[3].equalsIgnoreCase("y")) {
        bolBreakdown = true;
      } else if (options[3].equalsIgnoreCase("no") || options[3].equalsIgnoreCase("n")) {
        bolBreakdown = false;
      }

      // creating new home policy using home constructor and adding new policy to
      // policiesList
      carPolicy = new Car(sumInsured, options[1], options[2], bolBreakdown, options, intAge);
      currentProfile.addPolicy(carPolicy);
      MessageCli.NEW_POLICY_CREATED.printMessage(type.name().toLowerCase(), currentProfileName);
    }
  }
}
