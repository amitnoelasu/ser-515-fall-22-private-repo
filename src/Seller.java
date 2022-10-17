public class Seller extends Person{
    UserInfoItem userInfoItem;
    @Override
    void showMenu() {

    }

    public Seller(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
    }

    @Override
    ProductMenu createProductMenu() {
        return null;
    }
}
