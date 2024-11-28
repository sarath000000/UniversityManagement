import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends User {
    private int professor_id;
    private String name;
    public Professor()
    {
        this.userType=UserType.PROFESSOR;
        this.isLoggedOut=false;

    }
    @Override
    public boolean ValidateUser()
    {
        String password;
        System.out.println("Login Details: ");
        System.out.println("Enter your UserName:  ");
        this.userName=Main.sc.nextLine();
        System.out.println("Enter your Password:  ");
        password=Main.sc.nextLine();
        ResultSet rs=DataBase.getUser(this.userName,"Professor");
        String dbPass="";
        try
        {
            while(rs.next())
            {
                this.name=rs.getString("name");
                dbPass=rs.getString("password");
                this.professor_id=rs.getInt("professor_id");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            return dbPass.equals(password);
        }
    }
    @Override
    public void DisplayOptions()
    {
//        this.isLoggedOut=;
    }
}

