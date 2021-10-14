package day4;

public class EmployeeFactory {
  private static final String[] maleNames = {"Mike", "Tony", "Vova", "Alex", "Andrew", "Max", "Kevin", "Oleg", "Anatoliy", "Kostya"};
  private static final String[] famaleNames = {"Alina", "Elena", "Marina", "Anastasiya", "Anna", "Katya", "Vera", "Tanya", "Elizaveta", "Alla"};

  public static Employee[] generateEmployees(int size){
    Employee[] result = new Employee[size];
    for(int i=0; i<size; i++){
      boolean gender = getGender();
      String name = gender ? getName(maleNames) : getName(famaleNames);
      result[i] = new Employee(name, i, gender);
    }

    return result;
  }

  private static boolean getGender(){
    int result = (int) (Math.random() * (1+1));
    return result == 1;
  }

  private static String getName(String[] names){
    int index = (int) (Math.random() * names.length);
    return names[index];
  }
}
