import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
This class extends NodeVisitor and provides the implementation visiting and processing
Trading, ClassProductList
 */
public class ReminderVisitor extends NodeVisitor{
    Reminder m_Reminder;

    ClassProductList classProductList;

    @Override
    void visitProduct(Product product) {
        visitTrading(product.getTrading());
    }
    void visitClassProductList() {
        for(Product product: classProductList) {
            visitProduct(product);
        }
    }
    @Override
    /*
    Each trading node visited is processed by this function and then the user is notified
    if any trade expires
     */
    void visitTrading(Trading trading) {
//        System.out.println("Trading has been visited by Reminder Visitor");
        OfferingList offeringList = trading.getOfferingList();

        for(Offering offering: offeringList) {
            if(!offering.isSellerOffering) {
                Date expiryDate = trading.getWindowExpiryDate();
                Date dateNow = Calendar.getInstance().getTime();
                String timeStamp = new SimpleDateFormat("dd:HH:mm").format(expiryDate);
                if(expiryDate.compareTo(dateNow) < 0) {
                    System.out.println("Your trades on "+trading.product.getProductName()+" has expired! ");
                    offeringList.remove(offering);
                } else if(expiryDate.compareTo(dateNow) > 0) {
                    System.out.println("Your trades on "+trading.product.getProductName()+"which started on "+trading.getWindowStartDate()+" will be expiring on "+trading.getWindowExpiryDate());
                }
            } else {
                Date expiryDate = trading.getWindowExpiryDate();
                Date dateNow = Calendar.getInstance().getTime();
                String timeStamp = new SimpleDateFormat("dd:HH:mm").format(expiryDate);
                if(expiryDate.compareTo(dateNow) < 0) {
//                    System.out.println("Your trades on "+trading.product.getProductName()+" has expired! ");
                    offeringList.remove(offering);
                }
            }
        }
//        trading.accept(this);
    }

    @Override
    void visitFacade(Facade facade) {
//        System.out.println("Facade has been visited by ReminderVisitor");
        ClassProductList cpl = facade.theProductList;
        for(Product p: cpl) {
            Trading trading = p.getTrading();
            if(trading != null)
                trading.accept(this);
        }
    }
}
