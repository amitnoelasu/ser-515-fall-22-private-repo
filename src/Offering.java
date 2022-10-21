import java.io.Serializable;
import java.util.Date;

public class Offering implements Serializable {
    private static final long serialVersionUID = 1L;
    Product product;
    float sellerOffering;
    float buyerOffering;
    boolean isSellerOffering;

    Date offeringDate;

    public boolean isSellerOffering() {
        return isSellerOffering;
    }

    public void setSellerOffering(boolean sellerOffering) {
        isSellerOffering = sellerOffering;
    }

    public Date getOfferingDate() {
        return offeringDate;
    }

    public void setOfferingDate(Date offeringDate) {
        this.offeringDate = offeringDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getSellerOffering() {
        return sellerOffering;
    }

    public void setSellerOffering(float sellerOffering) {
        this.sellerOffering = sellerOffering;
    }

    public float getBuyerOffering() {
        return buyerOffering;
    }

    @Override
    public String toString() {
        return "Offering{" +
                "product=" + product +
                ", sellerOffering=" + sellerOffering +
                ", buyerOffering=" + buyerOffering +
                ", isSellerOffering=" + isSellerOffering +
                '}';
    }

    public void setBuyerOffering(float buyerOffering) {
        this.buyerOffering = buyerOffering;
    }

    Offering() {

    }

}
