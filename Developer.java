package day5;

import java.util.Random;

public class Developer extends Employee {
  protected Pos position = Pos.DEVELOPER;
  public Developer(String name, int id, boolean gender) {
    super(name, id, gender);
  }

  @Override
  public double getFullSalary(){
    double result = salary + (fixedBugs * defaultBugRate);
    return result * ((new Random().nextBoolean()) ? 2 : 0);
  }

  @Override
  public String toStringPosition(){
    return String.format("Name: %s\n%s", getName(), position.toString());
  }
}
