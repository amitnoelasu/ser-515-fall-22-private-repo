import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.*;

public class Facade {
    private int userType;
    private Product theSelectedProduct;
    private int nProductCategory;
    ClassProductList theProductList;
    Person thePerson;

    boolean login(String username, String password) throws Exception {

        Login loginObject = new Login();
        int tempUserType = loginObject.login(username, password);
        if(tempUserType != -1) {
            userType = tempUserType;
            createUser(new UserInfoItem(username,password,userType));
            try {
//                loadUserData(thePerson); // replaces the current user object with the history object
//                initProductList();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not load your product data");
            }

            return true;
        }
        return false;
    }
    List<Product> retrieveProducts() throws Exception{
        File productsData = new File("ProductData.txt");
        if(!productsData.exists())
            return null;
        FileInputStream fi = new FileInputStream(productsData);
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        List<Product> productList = (List<Product>) oi.readObject();
        oi.close();
        return productList;
    }
    void initProductList() throws Exception{
        File productPath = new File("ProductData.txt");
        if(!productPath.exists()) {
            FileOutputStream fileOut = new FileOutputStream(productPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(thePerson.productList);
            objectOut.close();
        }
    }
    boolean persistProducts(){
        try {
            File productsData;
//            List<Product> productList = retrieveProducts();

//            if(theProductList != null) {
//                for(Product product: theProductList) {
//                    if(product.getProductName().equals(theSelectedProduct.getProductName())) {
//                        theProductList.remove(product);
//                        break;
//                    }
//                }
//            } else {
//                productList = new ArrayList<Product>();
//            }
            for(Product product: theProductList) {
                if(product.getProductName().equals(theSelectedProduct.getProductName())) {
                    theProductList.remove(product);
                    break;
                }
            }


            theProductList.add(theSelectedProduct);

            productsData = new File("ProductData.txt");

            FileOutputStream fileOut = new FileOutputStream(productsData);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

//            objectOut.writeObject(productList);
            objectOut.writeObject(theProductList);
            objectOut.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    void addTrading() {
        Trading trading = theSelectedProduct.getTrading();
        if(trading == null)
            trading = new Trading(theSelectedProduct);
        OfferingList offeringList = trading.getOfferingList();
        Offering offering = new Offering();
        Scanner scanner = new Scanner(System.in);

        if(thePerson.getUserInfoItem().getUserType() == 1) { // seller
            System.out.println("Enter offer price(dollars) for the selected product");
            float price = Float.parseFloat(scanner.nextLine().trim());
            offering.setSellerOffering(price);
            offering.isSellerOffering = true;
        } else { // buyer
            System.out.println("Enter bid price(dollars) for the selected product");
            Float price = Float.parseFloat(scanner.nextLine().trim());
            offering.setBuyerOffering(price);
        }

        // add offering to offering list of trading object
        trading.getOfferingList().add(offering);
        // set trading object for the product
        theSelectedProduct.setTrading(trading);

        System.out.println("Added trading! Press E to exit");
        String exitChoice = scanner.nextLine().trim();
        if(exitChoice.equalsIgnoreCase("e")) {
//            boolean success = persistUserData(thePerson);
            boolean success = persistProducts();
            if(success) {
                System.out.println("Bye!!");
                System.out.println("All offerings for this product: ");
                System.out.println(Arrays.toString(theSelectedProduct.trading.offeringList.toArray()));

            } else {
                System.out.println("Oops! we could not save your trading information. Login and try again! Bye!");
            }
        } else {
            System.out.println("Oops! we could not save your trading information. Login and try again! Bye!");
        }

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
        thePerson = PersonFactory.createPerson(userInfoItem);
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
//            for(Product product: theProductList) {
            ListIterator<Product> productListIterator = theProductList.listIterator();
            while(productListIterator.hasNext()) {
                Product product = productListIterator.next();
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
        thePerson.showAddButton();
        thePerson.showViewButton();
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine().trim();
        if(choice.equalsIgnoreCase("A")) {
            this.addTrading();
        } else if(choice.equalsIgnoreCase("V")) {
            this.viewTrading();
        }

    }


}
