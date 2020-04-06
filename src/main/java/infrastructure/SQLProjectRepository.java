package infrastructure;

import domain.ProjectRepository;
import entity.Project;
import java.util.UUID;

public class SQLProjectRepository implements ProjectRepository {

  @Override
  public void save(Project project) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Project findById(UUID projectId) {
    throw new UnsupportedOperationException();
  }
}
