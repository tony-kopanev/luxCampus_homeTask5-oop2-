package day5;

public class Manager extends Employee {
  protected Pos position = Pos.MANAGER;
  public Manager(String name, int id, boolean gender) {
    super(name, id, gender);
    this.salary *= 1.512; // менеджерская надбавка
    this.defaultBugRate = 0;
    this.fixedBugs = 0;
  }

  @Override
  public double getFullSalary() {
    return salary;
  }

  @Override
  public String toStringPosition(){
    return String.format("Name: %s\n%s", getName(), position.toString());
  }
}