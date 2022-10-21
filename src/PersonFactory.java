/*
This is the factory Method pattern implemented by this class,
whose sole purpuse is to create objects of subclasses of person
depending on the usage
 */
public abstract class PersonFactory extends Person{
    public static Person createPerson(UserInfoItem userInfoItem){
        return userInfoItem.getUserType() == 0? new Buyer(userInfoItem): new Seller(userInfoItem);
    }

}
