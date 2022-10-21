import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends Person implements Serializable {
    @Override
    void showMenu() {
        int sNo = 1;
        for(Product product: productList) {
            System.out.println(sNo++ + ": "+product.getProductName());
        }
    }

    @Override
    void showAddButton() {
//        super.showAddButton();
        theProductMenu.showAddButton();
    }

    @Override
    void showViewButton() {
//        super.showViewButton();
        theProductMenu.showViewButton();
    }

    Seller() {
        this.productList = new ArrayList<Product>();
    }
    public Seller(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
    }

    @Override
    ProductMenu createProductMenu(Product selectedProduct) {
        // FACTORY METHOD PATTERN USED TO INSTANTIATE DIFFERENT TYPES OF PRODUCT MENUS
        // BASED ON THE SELECTED PRODUCT
        if(selectedProduct.productType.equalsIgnoreCase("Meat")) {
            theProductMenu = new MeatProductMenu();
            return theProductMenu;
        } else if(selectedProduct.productType.equalsIgnoreCase("Produce")) {
            theProductMenu = new ProduceProductMenu();
            return theProductMenu;
        }
        return null;
    }
}
