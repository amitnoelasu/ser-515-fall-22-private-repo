public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        //login
        boolean validUser = facade.login();
        if(validUser) {
            System.out.println("Welcome");
        } else {
            System.out.println("Sorry, you are not a  regisered user");
        }

    }
}
