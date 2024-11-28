import java.sql.SQLException;

import java.sql.ResultSet;
public class Admin extends User {
    private String name;
    private int admin_id;
    public Admin()
    {
        this.userType=UserType.ADMIN;
        this.isLoggedOut=false;
    }
    @Override
    public boolean ValidateUser()
    {
        String passWord;
        System.out.println("Login Details: ");
        System.out.println("Enter your UserName: ");
        this.userName=Main.sc.nextLine();
        System.out.println("Enter your Password: ");
        passWord=Main.sc.nextLine();
        ResultSet rs=DataBase.getUser(this.userName,"Administrator");
        String dbPass="";
        try
        {
            while(rs.next())
            {
                this.name=rs.getString("name");
                dbPass=rs.getString("password");
                this.admin_id=rs.getInt("admin_id");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            return dbPass.equals(passWord);
        }
    }
    @Override
    public void DisplayOptions()
    {

    }
}

