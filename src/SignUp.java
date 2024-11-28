import java.util.InputMismatchException;


public class SignUp {
    private static final  String[] type={"Student","Professor"};
    public static void signup()
    {System.out.println("1. Student");
        System.out.println("2 . Professor");
        int choice=0;
        try
        {
            choice=Main.sc.nextInt();
            if(choice>2 || choice<1)
            {
                throw new InvalidChoiceException("Invalid Choice");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Please enter a valid Input");
            System.exit(0);
        }
        catch(InvalidChoiceException e)
        {
            System.out.println("Please enter a number in the range of 1-2");
            System.exit(0);
        }
        String userName,passWord;
        System.out.println("Enter the UserName/ Email");
        userName=Main.sc.next();
        System.out.println("Enter the Password");
        passWord=Main.sc.next();
        boolean isUpdated=DataBase.setPassword(userName,passWord,type[choice-1]);
        if(isUpdated)
        {
            System.out.println("Password Updated");
            System.out.println("Login Again to Access");
        }
        else {
            System.out.println("Something went wrong");
            System.out.println("Check the email you have entered");
            System.out.println("Contact the Admin for further details");
        }

    }
}
