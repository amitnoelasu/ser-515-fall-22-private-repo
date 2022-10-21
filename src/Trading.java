import java.io.Serializable;

public class Trading implements Serializable {
    OfferingList offeringList;
    Product product;

    private static final long serialVersionUID = 3L;
    public Trading(Product product) {
        offeringList = new OfferingList();
    }

    public OfferingList getOfferingList() {
        return offeringList;
    }

    public void setOfferingList(OfferingList offeringList) {
        this.offeringList = offeringList;
    }

    void accept(NodeVisitor visitor) {

    }
}
