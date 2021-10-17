package day5;

public class Designer extends Employee {
  protected int workedDays, rate;
  protected Pos position = Pos.DESIGNER;

  public Designer(String name, int id, boolean gender) {
    super(name, id, gender);
    this.defaultBugRate = 0;
    this.fixedBugs = 0;
    this.workedDays = setRandom(15, 25);
    this.rate = setRandom(900, 2700);
  }

  @Override
  public double getFullSalary() {
    return salary + (rate * workedDays);
  }

  @Override
  public String toStringPosition(){
    return String.format("Name: %s\n%s", getName(), position.toString());
  }
}
