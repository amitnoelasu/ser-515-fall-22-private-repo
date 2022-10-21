import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Facade {
    private int userType;
    private Product theSelectedProduct;
    private int nProductCategory;
    ClassProductList theProductList;
    Person thePerson;
    public static final long MINUTE = 60*1000;
    /*
    The LOGIN functionality is called by the login method of this facade object
     */
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

    /*
    Retrieves the list of products in the entire system from history(file) and returns them
    This method is used to retrieve information about previous trades
     */
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

    /*
    This method is used to commit the updated (new trades added) product list into the file system
     */
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

    /*
    This method is invoked when the user clicks the "add" button on the product menu
    It is used to make an offering(or expected offering for a seller) and create a trading object
    for that selected product
     */
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
            System.out.println("Enter the duration for the window to be active(in minutes)");
            int time = Integer.parseInt(scanner.nextLine().trim());
            Date oldDate = Calendar.getInstance().getTime();
//            System.out.println(oldDate);
            trading.setWindowStartDate(oldDate);
            Date expiryDate = new Date(oldDate.getTime() + time*MINUTE);
//            System.out.println(expiryDate);
//            Date userGivenTime = new
//             = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            trading.setWindowExpiryDate(expiryDate);
            offering.setOfferingDate(oldDate);
            offering.isSellerOffering = true;
        } else { // buyer
            for(Product product: theProductList) {
                if(product.getProductName().equals(theSelectedProduct.getProductName())) {
                    if(product.trading == null) {
                        System.out.println("Sorry cannot bid for this product as there are no opening trades");
                        System.exit(1);
                    } else {
                        int count = 0;
                        for(Offering offering1: product.trading.offeringList) {
                            if(offering1.isSellerOffering) {
                                count++;
                            }
                        }
                        if(count == 0)
                            System.exit(1);
                    }

                }
            }
            System.out.println("Enter bid price(dollars) for the selected product");
            Float price = Float.parseFloat(scanner.nextLine().trim());
            Date oldDate = Calendar.getInstance().getTime();
            offering.setOfferingDate(oldDate);
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
//                System.out.println("All offerings for this product: ");
//                System.out.println(Arrays.toString(theSelectedProduct.trading.offeringList.toArray()));

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

    /*
    This function is used to visit the Facade object, then ClassProductList, Trading and Offering
    objects iteratively and finally remind the user of any upcoming trade expiries
     */
    void remind() {

        NodeVisitor visitor = new ReminderVisitor();
        this.accept(visitor);

//        visitor.visitProduct();
    }

    void createUser(UserInfoItem userInfoItem) {
        thePerson = PersonFactory.createPerson(userInfoItem);
    }

    /*
    Creates the product list and assigns the created object to the reference "theProductList"
     */
    void createProductList() throws Exception{
        this.theProductList = new ClassProductList();// creates a list of all products in the system
//        System.out.println(Arrays.toString(theProductList.productList.toArray()));
    }

    /*
    Iterates through the UserProduct.txt file and assigns the productList to thePerson.productList
     */
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

//        System.out.println(Arrays.toString(thePerson.productList.toArray()));

    }

    /*
    Prompts the user to select a product and return that selection
     */
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

    /*
    Product operation shows the product menu to the user, and the add button and view button
    Clicking on the add button takes the user to the addTrading method of this class
     */
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

    /*
    accepts visitors into the facade object
     */
    void accept(NodeVisitor visitor) {
//        System.out.println("Facade visitor accepted by Facade");
        visitor.visitFacade(this);
    }


}
