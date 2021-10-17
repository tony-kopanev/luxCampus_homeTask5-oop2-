package day5;

public enum Pos {
  DEVELOPER ("Developer"),
  MANAGER ("Manager"),
  DESIGNER ("Designer"),
  EMPLOYEE ("Employee");

  private final String title;

  Pos(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Position employee: " + title;
  }
}
