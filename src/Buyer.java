import java.util.ArrayList;
import java.util.List;

public class Buyer extends Person{

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
    public List<Product> getProductList() {
        return super.getProductList();
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
