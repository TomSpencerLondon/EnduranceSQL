package infrastructure;

import domain.ProjectRepository;
import entity.ComplexityEnum;
import entity.Project;
import entity.Task;
import exceptions.NoTasksException;

import java.security.InvalidKeyException;
import java.sql.*;
import java.time.LocalDate;
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
  public Project findById(int projectId) throws SQLException, InvalidKeyException {
    String findProjectSql = "SELECT * FROM Project WHERE id = ?";
    PreparedStatement projectQuery = connection.prepareStatement(findProjectSql);
    projectQuery.setInt(1, projectId);
    ResultSet projectResult = projectQuery.executeQuery();
    projectResult.next();

    String findTaskSql = "SELECT * FROM Task where project_id = ?";
    PreparedStatement taskQuery = connection.prepareStatement(findTaskSql);
    taskQuery.setInt(1, projectResult.getInt("id"));
    ResultSet taskResult = taskQuery.executeQuery();

    List<Task> listOfTasks = new ArrayList<>();
    while(taskResult.next()){
      listOfTasks.add(new Task(taskResult.getInt("id"),
              taskResult.getString("name"),
              taskResult.getString("description"),
              taskResult.getDate("deadline").toLocalDate(),
              ComplexityEnum.getByLevel(taskResult.getInt("complexity_id"))));
    }

    try {
      Project project = new Project(projectResult.getInt("id"),
              projectResult.getString("name"),
              projectResult.getString("description"),
              projectResult.getDate("deadline").toLocalDate(),
              listOfTasks);
      return project;
    } catch (NoTasksException e) {
      e.printStackTrace();
    }
    throw new InvalidKeyException();
  }
}
