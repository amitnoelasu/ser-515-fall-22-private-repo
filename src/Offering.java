import java.io.Serializable;

public class Offering implements Serializable {
    private static final long serialVersionUID = 1L;
    Product product;
    float sellerOffering;
    float buyerOffering;
    boolean isSellerOffering;
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
