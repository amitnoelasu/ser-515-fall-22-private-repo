import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        //login

        boolean validUser = facade.login();
        if(validUser) {
            System.out.println("Welcome");
            try {
                facade.createProductList();
            } catch (Exception e) {
                System.err.println("Error creating product List: "+e.getMessage());
            }

        } else {
            System.out.println("Sorry, you are not a registered user");
        }

    }
}
