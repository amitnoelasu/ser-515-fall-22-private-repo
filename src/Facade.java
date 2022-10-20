import java.io.*;
import java.util.*;

public class Facade {
    private int userType;
    private Product theSelectedProduct;
    private int nProductCategory;
    ClassProductList theProductList;
    Person thePerson;

    boolean login(String username, String password) {

        Login loginObject = new Login();
        int tempUserType = loginObject.login(username, password);
        if(tempUserType != -1) {
            userType = tempUserType;
            createUser(new UserInfoItem(username,password,userType));
            return true;
        }
        return false;
    }

    void addTrading() {
        System.out.println("Added trading");
    }

    void viewTrading() {
        System.out.println("Viewed trading");
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
        this.theProductList = new ClassProductList();// creates a list of all products in the system
//        System.out.println(Arrays.toString(theProductList.productList.toArray()));
    }

    void attachProductToUser() throws Exception {
        Set<String> userProductList = new HashSet<String>();
        File userProducts = new File(
                "UserProduct.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(userProducts));

        String st;

//        List<Product> productList = new ArrayList<Product>();
        while ((st = br.readLine()) != null) {
            String[] splitArr = st.split(":");
            if(splitArr[0].equals(thePerson.getUserInfoItem().getUsername())) {
                userProductList.add(splitArr[1]);
            }
        }

//        System.out.println(Arrays.toString(userProductList.toArray()));
        try {
//            System.out.println("proddd " + Arrays.toString(theProductList.getProductList().toArray()));
            List<Product> productL = new ArrayList<Product>();
            for(Product product: theProductList.getProductList()) {
                String productName = product.getProductName();
//                System.out.println("product name: "+productName);
                if(userProductList.contains(productName)) {
//                    System.out.println("inside ");
//                    System.out.println(thePerson.getProductList());
//                    System.out.println("outside");
                    productL.add(product);
//                    thePerson.getProductList().add(product);
                }

            }
            thePerson.setProductList(productL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Arrays.toString(thePerson.productList.toArray()));

    }

    Product selectProduct() {
//        int count = 1;
//        for(Product product: thePerson.getProductList()) {
//            System.out.println(count + ": "+product.getProductName());
//            count++;
//        }
        Scanner s = new Scanner(System.in);
        System.out.println("select the product: ");
        int choice = s.nextInt();
        for(int i = 0; i<thePerson.getProductList().size(); i++) {
            Product currentProduct = thePerson.getProductList().get(i);
            if(i == choice-1) {
                this.theSelectedProduct = currentProduct;
                return currentProduct;
            }
        }
        return null;
    }

    void productOperation() {
        thePerson.showMenu();
        theSelectedProduct = this.selectProduct();
        ProductMenu productMenu = thePerson.createProductMenu(theSelectedProduct);
        productMenu.showMenu();
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine().trim();
        if(choice.equalsIgnoreCase("A")) {
            this.addTrading();
        } else if(choice.equalsIgnoreCase("V")) {
            this.viewTrading();
        }

    }


}
