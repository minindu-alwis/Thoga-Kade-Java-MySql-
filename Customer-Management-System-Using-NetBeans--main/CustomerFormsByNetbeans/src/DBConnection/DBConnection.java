package DBConnection;
import java.sql.*;

public class DBConnection{
    private static DBConnection ob;
    private final Connection connection; 
    private DBConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost/thogakade", "root", "1234");
    }
    public Connection getConnection(){
        return  connection;
    }
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(ob==null){
            ob=new DBConnection();
        }
        return ob;
    }
}
