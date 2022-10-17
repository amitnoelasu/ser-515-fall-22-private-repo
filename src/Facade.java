import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
public class Facade {
    private int userType;
    private Product theSelectedProduct;
    private int nProductCategory;
    ClassProductList theProductList;
    Person thePerson;

    boolean login() {
        System.out.println("Enter username: ");

        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine().trim();

        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        //validate username and password from the database
        HashMap<String, String> buyerCredentials = new HashMap<String, String>();
        HashMap<String, String> sellerCredentials = new HashMap<String, String>();


        try {
            File buyerFile = new File(
                    "BuyerInfo.txt");
            BufferedReader br
                    = new BufferedReader(new FileReader(buyerFile));


            String st;

            while ((st = br.readLine()) != null) {
                String[] splitArr = st.split(":");
                buyerCredentials.put(splitArr[0], splitArr[1]);
            }

            File sellerFile = new File("SellerInfo.txt");
            br
                    = new BufferedReader(new FileReader(buyerFile));

            while ((st = br.readLine()) != null) {
                String[] splitArr = st.split(":");
                sellerCredentials.put(splitArr[0], splitArr[1]);
            }

            //set user type
            if(buyerCredentials.containsKey(username) && buyerCredentials.get(username).equals(password)) {
                userType = 0;
                return true;
            } else if(sellerCredentials.containsKey(username) && sellerCredentials.get(username).equals(password)) {
                userType = 1;
                return true;
            } else {
                return false;
            }

        }catch (Exception e) {
            System.err.println("Error reading text files" + e.getMessage());
        }



        return false;
    }

    void addTrading() {

    }

    void viewTrading() {

    }

    void dicideBidding() {

    }

    void discussBidding() {

    }

    void submitBidding() {

    }

    void remind() {

    }

    void createUser(UserInformation userInformation) {

    }

    void createProductList() {

    }

    void attachProductToUser() {

    }

    void selectProduct() {

    }

    void productOperation() {

    }


}
