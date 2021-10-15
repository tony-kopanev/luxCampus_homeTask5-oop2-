package day4;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    EmployeeService employeeService = new EmployeeService(EmployeeFactory.generateEmployees(10));

    Arrays.stream(employeeService.getByName("Tony")).forEach(System.out::println);
  }
}
