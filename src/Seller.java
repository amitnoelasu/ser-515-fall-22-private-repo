import java.util.ArrayList;

public class Seller extends Person{
    UserInfoItem userInfoItem;
    @Override
    void showMenu() {

    }
    Seller() {
        this.productList = new ArrayList<Product>();
    }
    public Seller(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
    }

    @Override
    ProductMenu createProductMenu() {
        return null;
    }
}
