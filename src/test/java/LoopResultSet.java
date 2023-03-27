import java.sql.*;

public class LoopResultSet {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@ 35.175.108.191:1521:XE";
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {

            connection= DriverManager.getConnection(url,"hr","hr");
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=statement.executeQuery("select * FROM REGIONS");
            System.out.println("Connection is successfully");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));
            }
            resultSet.beforeFirst();
            while (resultSet.next()){
                System.out.println("row Number="+resultSet.getRow());
                System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));
            }






        } catch (SQLException e) {
            System.out.println("Connection is Failed"+e.getMessage());
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Closing Over");
            }

        }
    }
}
