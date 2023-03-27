import java.sql.*;

public class ClosingConnection {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@ 35.175.108.191:1521:XE";
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            connection= DriverManager.getConnection(url,"hr","hr");
            System.out.println("Connection is successfully");
            statement=connection.createStatement();

            resultSet=statement.executeQuery("SELECT * FROM REGIONS");
            resultSet.next();




        } catch (SQLException e) {
            System.out.println("Connection is Failed"+e.getMessage());
        }finally {
            try {
                if (resultSet!=null) resultSet.close();
                if (statement!=null) statement.close();
                if (connection!=null) connection.close();
            } catch (SQLException e) {
                System.out.println("Closing Error");
            }

        }
    }
}
