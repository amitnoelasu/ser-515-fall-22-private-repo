import java.io.*;
import java.util.*;

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
                    = new BufferedReader(new FileReader(sellerFile));

            while ((st = br.readLine()) != null) {
                String[] splitArr = st.split(":");
                sellerCredentials.put(splitArr[0], splitArr[1]);
            }

            //set user type
            if(buyerCredentials.containsKey(username) && buyerCredentials.get(username).equals(password)) {
                userType = 0;
                createUser(new UserInfoItem(username, password, userType));
                return true;
            } else if(sellerCredentials.containsKey(username) && sellerCredentials.get(username).equals(password)) {
                userType = 1;
                createUser(new UserInfoItem(username, password, userType));
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

    void createUser(UserInfoItem userInfoItem) {
        thePerson = userInfoItem.getUserType() == 0? new Buyer(userInfoItem): new Seller(userInfoItem);
    }

    void createProductList() throws Exception{
        File productFile = new File(
                "ProductInfo.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(productFile));

        String st;

        List<Product> productList = new ArrayList<Product>();
        while ((st = br.readLine()) != null) {
            String[] splitArr = st.split(":");
            Product product = new Product(splitArr[0], splitArr[1]);
            productList.add(product);
        }

        theProductList = new ClassProductList(productList);
    }

    void attachProductToUser() throws Exception {
        List<String> userProductList = new ArrayList<String>();
        File userProducts = new File(
                "UserProduct.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(userProducts));

        String st;

//        List<Product> productList = new ArrayList<Product>();
//        while ((st = br.readLine()) != null) {
//            String[] splitArr = st.split(":");
//            if(splitArr[0].equals(thePerson.))
//            Product product = new Product(splitArr[0], splitArr[1]);
//            productList.add(product);
//        }
    }

    void selectProduct() {

    }

    void productOperation() {

    }


}
