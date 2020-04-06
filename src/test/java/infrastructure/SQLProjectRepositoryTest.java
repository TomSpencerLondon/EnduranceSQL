package infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class SQLProjectRepositoryTest {

  @Test
  public void store_project_to_sql() {

  }


}
//
//  private static Connection conn1;
//
//  public static void main(String[] args) throws SQLException {
//    String url1 = "jdbc:mysql://localhost:3306/test1";
//    String user = "root";
//    String password = "secret";
//
//    conn1 = DriverManager.getConnection(url1, user, password);
//    if (conn1 != null) {
//      System.out.println("Connected to the database test1");
//    }
//
//    SocialNetworkingApp app = SocialNetworkAppFactory.make();