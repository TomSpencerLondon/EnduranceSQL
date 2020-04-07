package infrastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import entity.ComplexityEnum;
import entity.Project;
import entity.Task;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import exceptions.NoTasksException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SQLProjectRepositoryTest {

  private static Connection connection;

  @BeforeAll
  static void beforeAll() throws SQLException {
    String url1 = "jdbc:mysql://localhost:3306/endurance";
    String user = "root";
    String password = "password";
    connection = DriverManager.getConnection(url1, user, password);
  }

  @Test
  public void store_project_to_sql_without_any_errors() throws SQLException, NoTasksException, InvalidKeyException {

    SQLProjectRepository sqlProjectRepository = new SQLProjectRepository(connection);
    int taskId = 1;
    int projectId = 1;

    Task task = new Task(taskId, "task 1", "This is a description 1", LocalDate.of(2020, 1, 1), ComplexityEnum.MINIMUM);
    Project project = new Project(projectId, "project 1", "project description 1", LocalDate.of(2020, 1, 1),
        Collections.singletonList(task));

    sqlProjectRepository.save(project);

    String sql = "SELECT * from Project project where project.id = ?";
    PreparedStatement query = connection.prepareStatement(sql);
    query.setInt(1, projectId);
    ResultSet resultSet = query.executeQuery();
    assertTrue(resultSet.first());
    Project projectFound = sqlProjectRepository.findById(project.id);
  }


  @AfterEach
  void tearDown() throws SQLException {
    String deleteTask = "DELETE FROM Task";
    String deleteProject = "DELETE FROM Project";
    PreparedStatement deleteTaskQuery = connection.prepareStatement(deleteTask);
    PreparedStatement deleteProjectQuery = connection.prepareStatement(deleteProject);
    deleteTaskQuery.execute();
    deleteProjectQuery.execute();
  }
}