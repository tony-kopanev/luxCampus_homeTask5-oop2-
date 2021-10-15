package day4;

public class Main {
  public static void main(String[] args) {
    EmployeeService employeeService = new EmployeeService(EmployeeFactory.generateEmployees(10));

    employeeService.printEmployees();
  }
}
