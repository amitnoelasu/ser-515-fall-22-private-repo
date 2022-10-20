import java.util.ListIterator;

public class ProductIterator implements ListIterator<Product> {

    ClassProductList productList;
    int next;
    int current;
    void moveToHead() {
        next = 0;
    }

    public ProductIterator(ClassProductList productList) {
        this.productList = productList;
        next = 0;
        current = next-1;
    }

    @Override
    public boolean hasNext() {
        if(next < productList.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product next() {
        Product currentProduct = this.productList.get(next);
        this.next = this.next+1;
        this.current = next-1;
        return currentProduct;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Product previous() {
        return null;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public int previousIndex() {
        return 0;
    }

    @Override
    public void remove() {
        productList.remove(current);
        next--;
        current--;
    }

    @Override
    public void set(Product product) {

    }

    @Override
    public void add(Product product) {

    }
}
