import java.util.List;

public abstract class Person {
    ProductMenu theProductMenu;
    List<Product> productList;
    UserInfoItem userInfoItem;
    abstract void showMenu();
    void showAddButton() {

    }

    public ProductMenu getTheProductMenu() {
        return theProductMenu;
    }

    public void setTheProductMenu(ProductMenu theProductMenu) {
        this.theProductMenu = theProductMenu;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public UserInfoItem getUserInfoItem() {
        return userInfoItem;
    }

    public void setUserInfoItem(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
    }

    void showViewButton() {

    }

    void showRadioButton() {

    }
    void showLabels() {

    }
    abstract ProductMenu createProductMenu(Product selectedProduct);


}
