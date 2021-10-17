package day5;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
  private static Employee[] employees = EmployeeFactory.generateEmployees(10);
  private static EmployeeService employeeService = new EmployeeService(employees);
  public static void main(String[] args) {
    employeeService.printByPos();
  }

  private static String previewPrintEmployees(){
    StringBuilder result = new StringBuilder();
    Arrays.stream(employees)
            .sorted(Comparator.comparingInt(Employee::getId))
            .forEach(result::append);

    return result.toString();
  }

  // void printEmployees() -> вывод на экран информации о сотрудниках
  private static void testPrintEmployees(String testId){
    System.out.println("-*-*-[expected print:]-*-*-");
    System.out.println(previewPrintEmployees());
    System.out.println("-*-*-[actual print:]-*-*-");
    employeeService.printEmployees();
  }


  // double calculateSalaryAndBonus() -> возвращает количество денег необходимое для выплаты зарплат для всех
  // сотрудников в этом месяце (пробегаем по всем сотрудникам, суммируем зарплату каждого с бонусом каждого)
  private static void assertEquals(String testId, double expected, double actual) {
    if (expected == actual) {
      System.out.println("TEST " + testId + " PASSED!");
    } else {
      System.out.println("TEST " + testId + " FAILED!");
      System.out.println("[expected]: " + expected);
      System.out.println("[actual]: " + actual);
    }
  };
  private static void testCalculateSalary(String testId){
    // назначаем все равную зарплату для тестов
    Employee[] copyEmployees = Arrays.copyOf(employees, employees.length);
    Arrays.stream(copyEmployees).forEach(employee -> employee.setFullSalary(10000, 20, 100));
    EmployeeService copyEmployeeService = new EmployeeService(copyEmployees);
    // тестим:
    double expected = (10000 +(100*20)) * 10;
    assertEquals(testId, expected, copyEmployeeService.calculateSalary());
  }


  //  Employee getById(long) -> возвращает сотрудника по заданному id
  private static void assertEquals(String testId, boolean expected, boolean actual) {
  if (expected == actual) {
    System.out.println("TEST " + testId + " PASSED!");
  } else {
    System.out.println("TEST " + testId + " FAILED!");
    System.out.println("[expected]: " + expected);
    System.out.println("[actual]: " + actual);
  }
};
  private static void testGetById(String testId){
    // назначим id от 0 до 9
    Employee[] copyEmployees = Arrays.copyOf(employees, employees.length);
    for(int i=0; i<copyEmployees.length; i++){
      copyEmployees[i].setId(i);
    }
    EmployeeService copyEmployeeService = new EmployeeService(copyEmployees);
    // тестим
    boolean actual = copyEmployeeService.getById(5).getId() == 5;
    assertEquals(testId, true, actual);
  }


  //  Employee[] getByName(String) -> возвращает сотрудника по заданному имени
  private static void testGetByName(String testId){
    Employee[] tonysEmployees = employeeService.getByName("Tony");
    boolean actual = Arrays.stream(tonysEmployees).allMatch(employee -> employee.getName().equals("Tony"));

    assertEquals(testId, true, actual);
  }


  // Employee[] sortByName() - сортирова по имени
  private static void assertEqualsArray(String testId, String[] expected, String[] actual){
    boolean result = Arrays.equals(expected, actual);
    if(result){
      System.out.println("TEST " + testId + " PASSED!");
    } else {
      System.out.println("TEST " + testId + " FAILED!");
      System.out.println("[expected]: " + Arrays.toString(expected));
      System.out.println("[actual]: " + Arrays.toString(actual));
    }
  }
  private static void testSortByName(String testId){
    // поменяем имена сотрудникам для тестов
    String[] famaleNames = {"Alina", "Elena", "Marina", "Anastasiya", "Anna", "Katya", "Vera", "Tanya", "Elizaveta", "Alla"};
    Employee[] copyEmployees = Arrays.copyOf(employees, employees.length);
    for(int i=0; i< copyEmployees.length; i++){
      copyEmployees[i].setName(famaleNames[i]);
    }
    EmployeeService copyEmployeeService = new EmployeeService(copyEmployees);

    String[] expected = Arrays.stream(famaleNames).sorted(String::compareTo).toArray(String[]::new);
    String[] actual = Arrays.stream(copyEmployeeService.sortByName()).map(Employee::getName).toArray(String[]::new);
    assertEqualsArray(testId, expected, actual);
  }


  // Employee[] sortByNameAndSalary() -> возвращают отсортированный массив с сотрудниками по критерию
  private static void testSortByNameAndSalary(String testId){
    // поменяем имена сотрудникам для тестов
    String[] famaleNames = {"Alina", "Elena", "Marina", "Anastasiya", "Anna", "Katya", "Vera", "Tanya", "Elizaveta", "Alla"};
    Employee[] copyEmployees = Arrays.copyOf(employees, employees.length);
    for(int i=0; i< copyEmployees.length; i++){
      copyEmployees[i].setName(famaleNames[i]);
    }
    EmployeeService copyEmployeeService = new EmployeeService(copyEmployees);

    // тестим
    boolean actual = true;
    Employee[] sorted = copyEmployeeService.sortByNameAndSalary();
    for(int i=1; i<sorted.length; i++){
      if(sorted[i].getName().equals(sorted[i-1].getName())){
        if(sorted[i].getSalary() < sorted[i-1].getSalary()){
          actual = false;
          break;
        }
      }
    }

    assertEquals(testId, true, actual);
  }


  // Employee edit(Employee) -> находит сотрудника по id, и подменяет информацию о нем на новую.
  // Старую версию сотрудника метод возвращает.
  private static void testEdit(String testId){
    // назначим id от 0 до 9
    Employee[] copyEmployees = Arrays.copyOf(employees, employees.length);
    for(int i=0; i<copyEmployees.length; i++){
      copyEmployees[i].setId(i);
    }
    EmployeeService copyEmployeeService = new EmployeeService(copyEmployees);
    // тестим
    Employee newEmployee = new Employee("Vasya", 5, true);
    newEmployee.setId(5); // поменяем ид на 5 для замены сотрудника
    Employee oldEmployee = copyEmployeeService.edit(newEmployee);
    boolean actual = copyEmployeeService.getById(5).getName().equals("Vasya");
    assertEquals(testId, true, actual);
  }


  // Employee remove(long id) -> находит сотрудника по id, и удаляет его из массива. Возвращает ссылку на удаленного сотрудника.
  private static void testRemove(String testId){
    // назначим id от 0 до 9
    Employee[] copyEmployees = Arrays.copyOf(employees, employees.length);
    for(int i=0; i<copyEmployees.length; i++){
      copyEmployees[i].setId(i);
    }
    EmployeeService copyEmployeeService = new EmployeeService(copyEmployees);
    // тест
    int lengthBefore = employeeService.getEmployees().length;
    employeeService.remove(5);
    int lengthAfter = employeeService.getEmployees().length;
    boolean actual = lengthBefore != lengthAfter;
    assertEquals(testId, true, actual);
  }
}