package entity;

import java.time.LocalDate;
import java.util.UUID;

public class Task {

  public final int id;
  public final String name;
  public final String description;
  public final LocalDate deadline;
  public final ComplexityEnum complexity;

  public Task(int id, String name, String description, LocalDate deadline, ComplexityEnum complexity) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.deadline = deadline;
    this.complexity = complexity;
  }

}
