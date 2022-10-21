import java.io.Serializable;
import java.util.Date;

/*
This class contains the trading information that is set up by the seller. The seller creates a trade
on a product
 */
public class Trading implements Serializable {
    OfferingList offeringList;
    Product product;
    Date windowExpiryDate;

    Date windowStartDate;

    public Date getWindowStartDate() {
        return windowStartDate;
    }

    public void setWindowStartDate(Date windowStartDate) {
        this.windowStartDate = windowStartDate;
    }

    public Date getWindowExpiryDate() {
        return windowExpiryDate;
    }

    public void setWindowExpiryDate(Date windowExpiryDate) {
        this.windowExpiryDate = windowExpiryDate;
    }

    private static final long serialVersionUID = 3L;
    public Trading(Product product) {
        this.product = product;
        offeringList = new OfferingList();
    }

    public OfferingList getOfferingList() {
        return offeringList;
    }

    public void setOfferingList(OfferingList offeringList) {
        this.offeringList = offeringList;
    }

    /*
    accepts the ReminderVisitor object and then calls the visitTradaing method of the visitor
     */
    void accept(NodeVisitor visitor) {
//        System.out.println("Trading has accepted Reminder Visitor");
        visitor.visitTrading(this);
    }
}
