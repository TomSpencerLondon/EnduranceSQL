package infrastructure;

import domain.ProjectRepository;
import entity.Project;
import entity.Task;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SQLProjectRepository implements ProjectRepository {

  private Connection connection;

  public SQLProjectRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void save(Project project) {
    // Task
//    id
//            name
//    description
//            deadline
//    project_id
//            complexity_id
//    country_id

    // Project
//    id
//            name
//    description
//            deadline

    // 1. Save the project
    // 2. Retreive the complexity that we need
    // 3. Save task passing id of project - pass complexity id

    String projectSql = "INSERT INTO Project (id, name, description, deadline)\n" +
            "VALUES (?, ?, ?, ?);";

    String[] taskAttributes = {
            "id",
            "name",
            "description",
            "deadline",
            "project_id",
            "complexity_id"
    };

    String taskSql = "INSERT INTO Task (" +  String.join(", ", taskAttributes)  + ")\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";

    try {
      connection.setAutoCommit(false);
      PreparedStatement insertProject = connection.prepareStatement(projectSql);
      PreparedStatement insertTask = connection.prepareStatement(taskSql);

      insertProject.setInt(1, project.id);
      insertProject.setString(2, project.name);
      insertProject.setString(3, project.description);
      insertProject.setString(4, project.deadline.toString());

      Task task = project.tasks.get(0);
      insertTask.setInt(1, task.id);
      insertTask.setString(2, task.name);
      insertTask.setString(3, task.description);
      insertTask.setDate(4, Date.valueOf(task.deadline));
      insertTask.setInt(5, project.id);
      insertTask.setInt(6, task.complexity.level);

      insertProject.execute();
      insertTask.execute();

      connection.commit();

    }catch(Exception e){
      System.out.println(e);
    }finally {

      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Project findById(UUID projectId) {
    throw new UnsupportedOperationException();
  }

}
