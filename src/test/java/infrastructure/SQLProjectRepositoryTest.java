package infrastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import entity.ComplexityEnum;
import entity.Project;
import entity.Task;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SQLProjectRepositoryTest {


  @Test
  public void store_project_to_sql_without_any_errors() throws SQLException {
    String url1 = "jdbc:mysql://localhost:3306/endurance";
    String user = "root";
    String password = "Buster";
    Connection connection = DriverManager.getConnection(url1, user, password);

    SQLProjectRepository sqlProjectRepository = new SQLProjectRepository(connection);
    UUID taskId = UUID.randomUUID();
    UUID projectId = UUID.randomUUID();

    Task task = new Task(taskId, "task 1", "This is a description 1", LocalDate.of(2020, 1, 1), ComplexityEnum.MINIMUM);
    Project project = new Project(projectId, "project 1", "project description 1", LocalDate.of(2020, 1, 1),
        Collections.singletonList(task));

    sqlProjectRepository.save(project);

    String sql = "SELECT * from Project prooject where prooject.id = ?";
    PreparedStatement query = connection.prepareStatement(sql);
    query.setString(1, projectId.toString());
    ResultSet resultSet = query.executeQuery();
    assertTrue(resultSet.first());
  }

}