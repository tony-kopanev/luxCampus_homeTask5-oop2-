package day4;

public class Employee {
  private int id, age, salary, fixedBugs, defaultBugRate;
  private String name;
  private boolean gender;

  public Employee(String name, int id, boolean gender){
    this.name = name;
    this.id =  Integer.parseInt(String.valueOf(setRandom(1, 99)) + id);
    this.gender = gender;
    this.age = setRandom(18, 45);
    this.salary = setRandom(18000, 53000);
    this.fixedBugs = setRandom(900, 2650);
    this.defaultBugRate = setRandom(0, 15);
  }

  private int setRandom(int min, int max){
    return (int) (min + Math.random() * (max+1-min));
  }
}
