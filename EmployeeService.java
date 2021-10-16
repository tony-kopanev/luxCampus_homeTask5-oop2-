package day4;

import java.util.Arrays;

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

  public Employee[] sortByName(){
    return Arrays.stream(employees).sorted((a,b) -> a.getName().compareTo(b.getName())).toArray(Employee[]::new);
  }

  public Employee[] sortByNameAndSalary(){
    Employee[] result = sortByName();
    boolean isSorted = false;

    while (!isSorted){
      isSorted = true;
      // сортируем по зп, если имена совпадают
      for(int i=1; i< result.length; i++){
        if(result[i].getName().equals(result[i-1].getName())){
          if(result[i].getSalary() < result[i-1].getSalary()){
            isSorted = false;
            Employee temp = result[i];
            result[i] = result[i-1];
            result[i-1] = temp;
          }
        }
      }
    }

    return result;
  }

  public Employee edit(Employee newEmployee){
    Employee result = null;
    for(int i=0; i<employees.length; i++){
      if(employees[i].getId() == newEmployee.getId()){
        result = employees[i];
        employees[i] = newEmployee;
        break;
      }
    }

    return result;
  }

  public Employee remove(long id){
    // находим сотрудника по id
    Employee result = Arrays.stream(employees)
            .filter(employee -> employee.getId() == id)
            .findAny().orElse(null);

    // если нашли удаляем его из списка сотруднико
    if(result != null){
      this.employees = Arrays.stream(employees)
              .filter(employee -> employee.getId() != id)
              .toArray(Employee[]::new);
    }

    return result;
  }
}
