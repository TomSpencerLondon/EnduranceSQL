package domain;

import entity.Project;
import java.util.UUID;

public interface ProjectRepository {

  void save(Project project);

  Project findById(UUID projectId);
}
