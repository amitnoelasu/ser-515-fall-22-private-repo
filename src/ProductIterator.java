import java.util.ListIterator;

public class ProductIterator implements ListIterator<Product> {

    void moveToHead() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Product next() {
        return null;
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

    }

    @Override
    public void set(Product product) {

    }

    @Override
    public void add(Product product) {

    }
}
