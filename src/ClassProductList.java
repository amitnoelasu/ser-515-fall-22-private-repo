import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ClassProductList extends ArrayList<Product> implements Serializable{
    @Override
    public ListIterator<Product> listIterator() {
        return new ProductIterator(this);
    }

    public ClassProductList() throws Exception{
//        productList = new ArrayList<Product>();
//        System.out.println("byeee");
        File productFile = new File(
                "ProductInfo.txt");
//        System.out.println(productFile +"file");
        BufferedReader br
                = new BufferedReader(new FileReader(productFile));
//        System.out.println(br + "bfreader");
        String st;

//        List<Product> productList = new ArrayList<Product>();
        while ((st = br.readLine()) != null) {
//            System.out.println(st);
            String[] splitArr = st.split(":");
            Product product = new Product(splitArr[0], splitArr[1]);
//            this.productList.add(product);
            this.add(product);
        }
        Facade f = new Facade();
        try {
            List<Product> pl = f.retrieveProducts();
            if(pl != null) {
//                for(Product product: this) {
//                    if(pl.contains(product)) {
//                        this.remove(product);
//                    }
//                }
                this.removeAll(this);
                this.addAll(pl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("hellooooooo");
//        theProductList = new ClassProductList(productList);
//        this.productList = productList;

    }

//    public List<Product> getProductList() {
//        return productList;
//    }

//    public void setProductList(List<Product> productList) {
//        this.productList = productList;
//    }

    void accept(NodeVisitor visitor) {

    }
}
