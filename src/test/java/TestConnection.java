import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {

        String url="jdbc:oracle:thin:@ 35.175.108.191:1521:XE";
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url,"hr","hr");
            System.out.println("Connection is successfully");



        } catch (SQLException e) {
            System.out.println("Connection is Failed"+e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("closing error");
            }
        }
    }
}
