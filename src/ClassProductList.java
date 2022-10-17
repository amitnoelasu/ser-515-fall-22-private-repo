import java.util.List;

public class ClassProductList {
    List<Product> productList;

    public ClassProductList(List<Product> productList) {
        this.productList = productList;
    }

    void accept(NodeVisitor visitor) {

    }
}
