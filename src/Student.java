import java.sql.SQLException;
import java.sql.ResultSet;
public class Student extends User{
    private String name;
    private double sgpa;
    private double cgpa;
    private int semester;
    private int student_id;
    public Student()
    {
        this.userType=UserType.STUDENT;
        this.isLoggedOut=false;
    }
    @Override
    public boolean ValidateUser()
    {
        String password;
        System.out.println("Login Details: ");
        System.out.println("Enter your UserName:  ");
        this.userName=Main.sc.next();
        System.out.println("Enter your Password:  ");
        password=Main.sc.next();
        ResultSet rs=DataBase.getUser(this.userName,"Student");
        String dbPass="";
        try {
            while (rs.next()) {
                this.name = rs.getString("name");
                dbPass = rs.getString("password");
                this.sgpa = rs.getDouble("sgpa");
                this.semester=rs.getInt("semester");
                this.cgpa=rs.getDouble("cgpa");
                this.student_id=rs.getInt("student_id");
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());        }
        finally{
            return dbPass.equals(password);
        }
    }
    @Override
    public void DisplayOptions()
    {
        System.out.println("Choose from the below Options");
        System.out.println("1. View All courses");
        System.out.println("2. Logout");
        int choice=Main.sc.nextInt();
        if(choice==1)
        {
            displayAllCourses();
        }
        else {
            isLoggedOut=true;
        }

    }
    public void displayAllCourses()
    {
        ResultSet rs=DataBase.getCourses(this.semester);
        int count=0;
        try {
            while (rs.next()) {
                System.out.println("Course ID:  "+rs.getString("course_id"));
                System.out.println("Title: "+rs.getString("title"));
                System.out.println("Professor "+rs.getString("name"));
                System.out.println("Credits: "+rs.getString("credits"));
                System.out.println("Prerequisites: "+rs.getString("prerequisites"));
                System.out.println("Timings: "+rs.getString("timings"));
                count++;
            }
            if(count==0)
            {
                System.out.println("No courses Found");
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
