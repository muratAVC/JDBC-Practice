import java.sql.*;

public class ResultSetMetaData_ColumnInfo {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@ 35.175.108.191:1521:XE";
//        Connection connection=null;
//        Statement statement=null;
//        ResultSet resultSet=null;
        try(Connection connection= DriverManager.getConnection(url,"hr","hr");
           Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
           ResultSet resultSet=statement.executeQuery("SELECT * fROM REGIONS");
           ) {
            System.out.println("Connection is successfully");

            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            int colCount=resultSetMetaData.getColumnCount();
//            String colName=resultSetMetaData.getColumnName(colCount);
//            System.out.println("colCount = " + colCount);
//            System.out.println("colName = " + colName);
            for (int i = 1; i <=colCount ; i++) {
                System.out.print(resultSetMetaData.getColumnName(i)+"\t");
            }
            System.out.println("");
            while (resultSet.next()) {
                for (int colIndex = 1; colIndex <= colCount; colIndex++) {
                    System.out.print(resultSet.getString(colIndex)+"\t");
                }
                System.out.println("");
            }




        } catch (SQLException e) {
            System.out.println("Connection is Failed"+e.getMessage());
        }
//        finally {
//            try {
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("Closing Error");
//            }
//
//        }
    }
}
