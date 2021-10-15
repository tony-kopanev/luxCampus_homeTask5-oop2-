package day4;

import java.util.Arrays;

public class EmployeeService {
  private Employee[] employees = null;
  public EmployeeService(Employee[] employees){ this.employees = employees; }

  public void printEmployees(){
    // сортируем по идшнику
    Arrays.stream(employees).sorted((a, b) -> a.getId() - b.getId()).forEach(System.out::println);
  }

}
