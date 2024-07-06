package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {

  // initalising variables
  private String userName;
  private String age = "";
  private ArrayList<Policy> policiesList = new ArrayList<Policy>();
  private String policyType = "NoType";

  // contructor for profile
  public Profile(String userName, String age) {
    this.userName = userName;
    this.age = age;
  }

  // method to print polcies depending on type
  public void printPolicies() {

    // printing policies through policiesList
    for (Policy policy : policiesList) {
      if (policy instanceof Home) {
        printHomePolicy((Home) policy);
      } else if (policy instanceof Life) {
        printLifePolicy((Life) policy);
      } else if (policy instanceof Car) {
        printCarPolicy((Car) policy);
      }
    }
  }

  // method to calculate discount price
  private double getDiscountPrice(Policy policy) {
    double discountPrice;

    // setting value for discount type depending on number of policies
    if (this.getPolicyCount() == 2) {
      discountPrice = policy.getBasePremium() - (policy.getBasePremium() * 0.1);
    } else if (this.getPolicyCount() >= 3) {
      discountPrice = policy.getBasePremium() - (policy.getBasePremium() * 0.2);
    } else {
      discountPrice = policy.getBasePremium();
    }

    return discountPrice;
  }

  // method for printing home policy
  private void printHomePolicy(Home policy) {
    double discountPrice = this.getDiscountPrice(policy);
    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
        policy.getAddress(),
        Integer.toString(policy.getSumInsured()),
        Integer.toString((int) policy.getBasePremium()),
        Integer.toString((int) discountPrice));
  }

  // method for printing home policy
  private void printLifePolicy(Life policy) {
    double discountPrice = this.getDiscountPrice(policy);
    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
        Integer.toString(policy.getSumInsured()),
        Integer.toString((int) policy.getBasePremium()),
        Integer.toString((int) discountPrice));
  }

  // method for printing car policy
  private void printCarPolicy(Car policy) {
    double discountPrice = this.getDiscountPrice(policy);
    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
        policy.getMakeModel(),
        Integer.toString(policy.getSumInsured()),
        Integer.toString((int) policy.getBasePremium()),
        Integer.toString((int) discountPrice));
  }

  // method to get total price
  public String getTotalPrice() {
    double sum = 0;

    // adding up all discount prices for each profile
    for (Policy policy : policiesList) {
      double discountPrice1 = this.getDiscountPrice(policy);
      sum += discountPrice1;
    }

    return Double.toString(sum);
  }

  // method for returning values for userName and age
  public String getUserName() {
    return this.userName;
  }

  // method for getting age of profile
  public String getAge() {
    return this.age;
  }

  // method for counting elements in policiesList
  public int getPolicyCount() {
    return policiesList.size();
  }

  // method for adding policies to policiesList
  public void addPolicy(Policy policy) {
    policiesList.add(policy);
  }

  // method to return policiesList
  public ArrayList<Policy> getPolicy() {
    return policiesList;
  }

  // get all policies
  public ArrayList<Policy> getPolicies() {
    return policiesList;
  }

  // method for getting amount of home polices in policiesList
  public int getHomePolicyCount() {
    int homePolicyCount = 0;
    for (Policy policy : policiesList) {
      if (policy instanceof Home) {
        homePolicyCount++;
      }
    }
    return homePolicyCount;
  }

  // method for getting amount of life policies in policiesList
  public int getLifePolicyCount() {
    int lifePolicyCount = 1;
    for (Policy policy : policiesList) {
      if (policy instanceof Life) {
        lifePolicyCount++;
      }
    }
    return lifePolicyCount;
  }

  // method to get policy type
  public String getPolicyType() {
    for (Policy policy : policiesList) {
      if (policy instanceof Home) {
        policyType = "Home";
      } else if (policy instanceof Life) {
        policyType = "Life";
      } else if (policy instanceof Car) {
        policyType = "Car";
      }
    }

    return policyType;
  }
}
