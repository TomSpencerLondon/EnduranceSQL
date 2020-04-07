import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Main {

  private static Connection conn1;

  public static void main(String[] args) throws SQLException {
    SpringApplication.run(Main.class, args);
//    String url1 = "jdbc:mysql://localhost:3306/endurance";
//    String user = "root";
//    String password = "Buster";
//
//    conn1 = DriverManager.getConnection(url1, user, password);
//    if (conn1 != null) {
//      System.out.println("Connected to the database test1");
//    }


  }

}
