package nz.ac.auckland.se281;

public class Life extends Policy {

  private String[] options;
  private int intAge;

  // constructor for life policy
  public Life(int sumInsured, String[] options, int intAge) {
    super(sumInsured);
    this.options = options;
    this.intAge = intAge;
  }

  // method to return sum insured
  public int getSumInsured() {
    return Integer.parseInt(options[0]);
  }

  // method to calculate base premium
  public double getBasePremium() {

    // calculating base premium for life policy
    double basePremium = (((1 + (intAge * 0.01)) * 0.01) * sumInsured);
    return basePremium;
  }
}
