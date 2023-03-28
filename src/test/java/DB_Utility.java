import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Utility {
    static Connection connection;
    static ResultSet resultSet;
    static Statement statement;

    public static void createConnection(){
        String url="jdbc:oracle:thin:@ 35.175.108.191:1521:XE";
       try{connection= DriverManager.getConnection(url,"hr","hr");
            System.out.println("Connection is successfully");
        } catch (SQLException e) {
            System.out.println("Connection is Failed"+e.getMessage());
        }
    }

    public static ResultSet runQuery(String query){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting resultset");
        }

        return resultSet;
    }

    public static void destroy(){
        try {
            if (resultSet!=null) resultSet.close();
            if (statement!=null)statement.close();
            if (connection!=null)connection.close();
        } catch (SQLException e) {
            System.out.println(" ");
        }
    }

    public static int getRowCount(){
        int count=0;
        try {
            resultSet.last();
            count=resultSet.getRow();
        } catch (SQLException e) {
            System.out.println("error while getting row count"+ e.getMessage());
        }
        return count;
    }
    public static int getColumnCNT(){
        int result=0;
        try {
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            result=resultSetMetaData.getColumnCount();
        } catch (SQLException e) {
            System.out.println("error whole getting column count "+e.getMessage());
        }
        return result;
    }

    public static List<String> getColumnNames(){
        List<String> columnNames=new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData =resultSet.getMetaData();
            for (int i = 1; i < resultSetMetaData.getColumnCount() ; i++) {
                columnNames.add(resultSetMetaData.getColumnName(i));
            }


        } catch (SQLException e) {
            System.out.println("Error whole getting column names"+e.getMessage());
        }

        return columnNames;
    }

    public static List<String> getRowDataAsList(int rowNum){
        List<String> result =new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData =resultSet.getMetaData();
            resultSet.absolute(rowNum);
            for (int i = 1; i <resultSetMetaData.getColumnCount() ; i++) {
                result.add(resultSet.getString(i));
            }
        } catch (SQLException e) {
            System.out.println("Error whole getting row data"+e.getMessage());
        }
        return result;
    }

    public static List<String> getColumnDataAsList(int columnIndex){
        List<String> result =new ArrayList<>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()){
                result.add(resultSet.getString(columnIndex));
            }
        } catch (SQLException e) {
            System.out.println("Error whole getting row data"+e.getMessage());
        }
        return result;
    }

    public static String getColumnDataAtRow(int rowNum,int columnIndex){
        String result="";
        try {
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            resultSet.absolute(rowNum);
            result=resultSet.getString(columnIndex);

        } catch (SQLException e) {
            System.out.println("Error getting cell data "+e.getMessage());
        }

        return result;
    }

    public static String getColumnDataAtRow(int rowNum,String columnName){
        String result="";
        try {
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            resultSet.absolute(rowNum);
            result=resultSet.getString(columnName);

        } catch (SQLException e) {
            System.out.println("Error getting cell data "+e.getMessage());
        }

        return result;
    }

    public static void main(String[] args) throws SQLException {
        createConnection();
        ResultSet rslst = runQuery("Select * From EMPLOYEES");
        rslst.next();
//        System.out.println(rslst.getString("FIRST_NAME"));
//        System.out.println(getRowCount());
//        System.out.println("getColumnCNT() = " + getColumnCNT());
//        System.out.println(getColumnNames());
        System.out.println(getRowDataAsList(10));
        System.out.println(getColumnDataAtRow(10,3));
        System.out.println(getColumnDataAtRow(10,"FIRST_NAME"));
        System.out.println(getColumnDataAsList(3)+"-");

        destroy();
    }
}
