import java.util.ArrayList;

public class Seller extends Person{
    @Override
    void showMenu() {
        int sNo = 1;
        for(Product product: productList) {
            System.out.println(sNo++ + ": "+product.getProductName());
        }
    }
    Seller() {
        this.productList = new ArrayList<Product>();
    }
    public Seller(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
    }

    @Override
    ProductMenu createProductMenu(Product selectedProduct) {
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
