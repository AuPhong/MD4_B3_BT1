package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "Kien", 100));
        productList.add(new Product(2, "Binh", 200));
        productList.add(new Product(3, "Manh", 300));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public int getIndexById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId()==id){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void update(Product product) {
        int index = getIndexById(product.getId());
        productList.set(index, product);
    }

    @Override
    public void remove(int id) {
        Product product = findById(id);
        productList.remove(product);
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().contains(name)){
                products.add(productList.get(i));
            }
        }
        return products;
    }
}
