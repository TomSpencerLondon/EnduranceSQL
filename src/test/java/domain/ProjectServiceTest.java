package domain;

import static org.mockito.Mockito.verify;

import entity.ComplexityEnum;
import entity.Project;
import entity.Task;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import exceptions.NoTasksException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

  @Mock
  private ProjectRepository sqlProjectRepository;

  @Test
  public void saves_a_project() throws NoTasksException {
    int taskId = 1;
    int projectId = 1;

    Task task = new Task(taskId, "task 1", "This is a description 1", LocalDate.of(2020, 1, 1), ComplexityEnum.MINIMUM);
    Project project = new Project(projectId, "project 1", "project description 1", LocalDate.of(2020, 1, 1),
        Collections.singletonList(task));

    ProjectService projectService = new ProjectService(sqlProjectRepository);
    projectService.save(project);

    verify(sqlProjectRepository).save(project);
  }


}