public abstract class PersonFactory extends Person{
    public static Person createPerson(UserInfoItem userInfoItem){
        return userInfoItem.getUserType() == 0? new Buyer(userInfoItem): new Seller(userInfoItem);
    }

}
