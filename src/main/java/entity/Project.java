package entity;

import exceptions.NoTasksException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Project {

  public final int id;
  public final String name;
  public final String description;
  public final LocalDate deadline;
  public final List<Task> tasks;

  public Project(int id, String name, String description, LocalDate deadline, List<Task> tasks) throws NoTasksException {
    if (tasks.isEmpty()){
      throw new NoTasksException();
    }

    this.id = id;
    this.name = name;
    this.description = description;
    this.deadline = deadline;
    this.tasks = tasks;
  }
}
