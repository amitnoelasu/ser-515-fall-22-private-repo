public class Buyer extends Person{
    UserInfoItem userInfoItem;
    @Override
    void showMenu() {

    }

    public Buyer(UserInfoItem userInfoItem) {
        this.userInfoItem = userInfoItem;
    }

    @Override
    ProductMenu createProductMenu() {
        return null;
    }
}
