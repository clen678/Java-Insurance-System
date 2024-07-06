package nz.ac.auckland.se281;

public abstract class Policy {

  protected int sumInsured;
  private double basePremium;

  // constructor for policy
  public Policy(int sumInsured) {
    this.sumInsured = sumInsured;
  }

  // method for returning sumInsured
  public int getSumInsured() {
    return sumInsured;
  }

  // method for returning basePremium
  public double getBasePremium() {
    return basePremium;
  }
}
