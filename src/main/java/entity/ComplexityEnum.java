package entity;

public enum ComplexityEnum {
  MINIMUM(1),
  MEDIUM(2),
  MAXIMUM(3);

  public final int level;

  ComplexityEnum(int level) {
    this.level = level;
  }

  public static ComplexityEnum getByLevel(int number) {
    for (ComplexityEnum c : ComplexityEnum.values()){
      if (c.level == number){
        return c;
      }
    }
    throw new IllegalArgumentException();
  }
}
