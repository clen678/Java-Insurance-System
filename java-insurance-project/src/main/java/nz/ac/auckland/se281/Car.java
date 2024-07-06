package nz.ac.auckland.se281;

public class Car extends Policy {

  private String makeModel;
  private String licencePlate;
  private boolean breakdown;
  private String[] options;
  private int intAge;
  private double basePremium;

  // constructor for car
  public Car(
      int sumInsured,
      String makeModel,
      String licencePlate,
      boolean breakdown,
      String[] options,
      int intAge) {
    super(sumInsured);
    this.makeModel = makeModel;
    this.licencePlate = licencePlate;
    this.breakdown = breakdown;
    this.options = options;
    this.intAge = intAge;
  }

  // method to return sumInsured
  public int getSumInsured() {
    return Integer.parseInt(options[0]);
  }

  // method to return makeModel
  public String getMakeModel() {
    return makeModel;
  }

  // method to return licencePlate
  public String getLicencePlate() {
    return licencePlate;
  }

  // method to return breakdown
  public String getBreakdown() {
    return options[2];
  }

  // method to calculate base premium
  public double getBasePremium() {

    // setting value for basePremium depending on age and breakdown
    if (intAge < 25) {
      basePremium = (sumInsured * 0.15);
    } else if (intAge > 25 || intAge == 25) {
      basePremium = (sumInsured * 0.10);
    }

    if (breakdown) {
      basePremium = basePremium + 80;
    }

    return basePremium;
  }
}
