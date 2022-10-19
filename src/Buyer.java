import java.util.ArrayList;
import java.util.List;

public class Buyer extends Person{

    @Override
    void showMenu() {

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
    ProductMenu createProductMenu() {
        return null;
    }
}
