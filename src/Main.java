import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Facade facade = new Facade();
        //login
        System.out.println("Enter username: ");

        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine().trim();

        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        boolean validUser = facade.login(username, password);
        if(validUser) {
            System.out.println("Welcome");

            facade.createProductList();
            facade.attachProductToUser();
            facade.productOperation();

//                Product selectedProduct = facade.selectProduct();
//                if (selectedProduct != null) {
//                    facade.productOperation();
//                }
//                System.out.println(selectedProduct);

        }

//        } else {
//            Scanner s = new Scanner(System.in);
//            System.out.println("Sorry, you are not a registered user.");
//            System.out.println("Do you want to try again? Y/N");
//            String choice = s.nextLine().trim();
//            if(choice.equalsIgnoreCase("y")) {
//                facade.login();
//            } else {
//                System.out.println("Bye!");
//            }
//
//        }

    }
}
