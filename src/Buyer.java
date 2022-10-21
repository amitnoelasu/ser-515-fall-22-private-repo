import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Buyer extends Person implements Serializable {

    @Override
    public String toString() {
        return "Buyer{" +
                "userInfoItem=" + userInfoItem +
                '}';
    }

    @Override
    void showMenu() {
        int sNo = 1;
        for(Product product: productList) {
            System.out.println(sNo++ + ": "+product.getProductName());
        }
    }
    Buyer() {
        this.productList = new ArrayList<Product>();
    }
    public Buyer(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
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

    @Override
    public List<Product> getProductList() {
        return super.getProductList();
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
