package day4;

import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    EmployeeService employeeService = new EmployeeService(EmployeeFactory.generateEmployees(10));

    Arrays.stream(employeeService.sortByNameAndSalary()).forEach(System.out::println);
  }
}
