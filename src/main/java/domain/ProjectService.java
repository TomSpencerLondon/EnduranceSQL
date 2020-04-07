package domain;

import entity.Project;
import java.util.UUID;

public class ProjectService {

  private ProjectRepository projectRepository;

  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public void save(Project project) {
    projectRepository.save(project);
  }

  public Project retrieveById(int projectId) {
    throw new UnsupportedOperationException("Implement me!");
  }
}
