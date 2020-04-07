package Acceptance;

import domain.ProjectRepository;
import domain.ProjectService;
import entity.ComplexityEnum;
import entity.Project;
import entity.Task;
import exceptions.NoTasksException;
import infrastructure.SQLProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProjectFeature {

  @Test
  public void create_project_with_tasks() throws SQLException, NoTasksException {
    String url1 = "jdbc:mysql://localhost:3306/endurance";
    String user = "root";
    String password = "Buster";
    Connection connection = DriverManager.getConnection(url1, user, password);

    ProjectRepository projectRepository = new SQLProjectRepository(connection);
    ProjectService projectService = new ProjectService(projectRepository);
    int taskId = 1;
    int projectId = 1;

    Task task = new Task(taskId, "task 1", "This is a description 1", LocalDate.of(2020, 1, 1), ComplexityEnum.MINIMUM);
    Project project = new Project(projectId, "project 1", "project description 1", LocalDate.of(2020, 1, 1),
        Collections.singletonList(task));

    projectService.save(project);
    Project retrievedProject = projectService.retrieveById(projectId);

    assertEquals(project, retrievedProject);
  }




}
