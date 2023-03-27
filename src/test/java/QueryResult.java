import java.sql.*;


public class QueryResult {
    public static void main(String[] args) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {
            connection= DriverManager.getConnection("jdbc:oracle:thin:@ 35.175.108.191:1521:XE","hr","hr");
            statement=connection.createStatement();

            resultSet=statement.executeQuery("SELECT * FROM REGIONS");
            resultSet.next();
            System.out.print(resultSet.getString(1)+"--");
            System.out.println(resultSet.getString("REGION_NAME"));
            resultSet.next();
            System.out.println(resultSet.getString("REGION_NAME"));
            resultSet.next();
            System.out.println(resultSet.getString("REGION_NAME"));
            resultSet.next();
            System.out.println(resultSet.getString("REGION_NAME"));
            resultSet.next();
            System.out.println(resultSet.getString("REGION_NAME"));




        } catch (SQLException e) {
            System.out.println("Error Has Occured "+e.getMessage());
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Closing Error");
            }

        }

    }
}
