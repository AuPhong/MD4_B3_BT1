package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(Product product);

    void remove(int id);

    List<Product> searchByName(String name);
    int getIndexById(int id);
}
