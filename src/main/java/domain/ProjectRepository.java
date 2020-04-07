package domain;

import entity.Project;

import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.UUID;

public interface ProjectRepository {

  void save(Project project);

  Project findById(int projectId) throws SQLException, InvalidKeyException;
}
