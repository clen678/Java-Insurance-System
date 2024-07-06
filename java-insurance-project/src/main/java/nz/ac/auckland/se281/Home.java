package nz.ac.auckland.se281;

public class Home extends Policy {

  private String address;
  private Boolean rental;
  private String[] options;
  private double basePremium;

  // constructor for home policy
  public Home(int sumInsured, String address, Boolean rental, String[] options) {
    super(sumInsured);
    this.address = address;
    this.rental = rental;
    this.options = options;
  }

  // method to return address
  public String getAddress() {
    return address;
  }

  // method to return sum insured
  public int getSumInsured() {
    return Integer.parseInt(options[0]);
  }

  // method to return rental
  public String getRental() {
    return options[2];
  }

  // method to calculate base premium
  public double getBasePremium() {

    // calculate base premium based on rental
    if (rental == true) {
      this.basePremium = (sumInsured * 0.02);
    } else if (rental == false) {
      this.basePremium = (sumInsured * 0.01);
    }

    return basePremium;
  }
}
