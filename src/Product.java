import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 4L;
    String productType;
    String productName;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Trading getTrading() {
        return trading;
    }

    public void setTrading(Trading trading) {
        this.trading = trading;
    }

    Trading trading;
    public Product(String productType, String productName) {
        this.productType = productType;
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
