import java.sql.*;

public class NaviResultSet1 {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@ 35.175.108.191:1521:XE";
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            connection= DriverManager.getConnection(url,"hr","hr");
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=statement.executeQuery("SELECT * fROM REGIONS");
            resultSet.first();
            System.out.println(resultSet.getString("REGION_ID")+"--"+resultSet.getString("REGION_NAME"));
            resultSet.last();
            System.out.println(resultSet.getString("REGION_ID")+"--"+resultSet.getString("REGION_NAME"));
            resultSet.previous();
            System.out.println(resultSet.getString("REGION_ID")+"--"+resultSet.getString("REGION_NAME"));
            resultSet.absolute(3);
            System.out.println(resultSet.getString("REGION_ID")+"--"+resultSet.getString("REGION_NAME"));
            resultSet.beforeFirst();
            resultSet.next();
            System.out.println(resultSet.getString("REGION_ID")+"--"+resultSet.getString("REGION_NAME"));
            resultSet.afterLast();
            resultSet.previous();
            System.out.println(resultSet.getString("REGION_ID")+"--"+resultSet.getString("REGION_NAME"));




            System.out.println("Connection is successfully");
        } catch (SQLException e) {
            System.out.println("Connection is Failed"+e.getMessage());
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
