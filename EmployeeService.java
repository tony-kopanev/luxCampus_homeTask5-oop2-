package day4;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EmployeeService {
  private Employee[] employees = null;
  public EmployeeService(Employee[] employees){ this.employees = employees; }

  public void printEmployees(){
    // сортируем по идшнику
    Arrays.stream(employees).sorted((a, b) -> a.getId() - b.getId()).forEach(System.out::println);
  }

  public double calculateSalary(){
    return Arrays.stream(employees).reduce(0.0, (sum, cur) -> {
      double salary = (double) cur.getSalary();
      double fixedBugs = (double) cur.getFixedBugs();
      double bugRate = (double) cur.getDefaultBugRate();

      return (double) (sum + (salary + (fixedBugs * bugRate)));
    }, Double::sum);
  }

  public Employee getById(int id) {
    return Arrays.stream(employees)
            .filter(employee -> employee.getId() == id)
            .findAny()
            .orElse(null);
  }

  public Employee[] getByName(String name){
    return Arrays.stream(employees)
            .filter(employee -> employee.getName().equals(name))
            .toArray(Employee[]::new);
  }
}
