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
                facade.attachProductToUser();
                Product selectedProduct = facade.selectProduct();
                if(selectedProduct != null) {
                    facade.productOperation();
                }
//                System.out.println(selectedProduct);
            } catch (Exception e) {
                System.err.println("Error creating product List: " + e.getMessage());
            }
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
