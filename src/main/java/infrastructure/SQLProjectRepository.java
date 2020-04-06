package infrastructure;

import domain.ProjectRepository;
import entity.Project;
import java.sql.Connection;
import java.util.UUID;

public class SQLProjectRepository implements ProjectRepository {

  public SQLProjectRepository(Connection connection) {
  }

  @Override
  public void save(Project project) {
  }

  @Override
  public Project findById(UUID projectId) {
    throw new UnsupportedOperationException();
  }

}
