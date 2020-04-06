package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Project {

  private final UUID id;
  private final String name;
  private final String description;
  private final LocalDate deadline;
  private final List<Task> tasks;

  public Project(UUID id, String name, String description, LocalDate deadline, List<Task> tasks) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.deadline = deadline;
    this.tasks = tasks;
  }
}
