import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class DataBase
{
    private static final String url="jdbc:mysql://localhost:3306/oop";
    private static final String user="root";
    private static final String password="new_password";
    public static Connection connection;
    public static Statement statement;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static ResultSet getUser(String userName,String userType)
    {
        ResultSet rs=null;
        String Query="select * from "+userType+" where email='"+userName+"'";
        try {
            rs = statement.executeQuery(Query);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            return rs;
        }
    }
    public static boolean setPassword(String userName,String password,String type)
    {
        int rowsAffected=0;
        String Query=String.format("UPDATE "+type+" SET password='%s' WHERE email='%s' AND password IS NULL",password,userName);
        try {
            rowsAffected = statement.executeUpdate(Query);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            return rowsAffected>0;
        }
    }
    public static ResultSet getCourses(int sem)
    {
        ResultSet rs=null;
        try
        {
            String query=String.format("select * from Course c JOIN Professor p on c.professor_id=p.professor_id WHERE semester = %d",sem);
            rs=statement.executeQuery(query);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            return rs;
        }
    }

}
