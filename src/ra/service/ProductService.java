package ra.service;

import ra.model.Catalog;
import ra.model.Product;

import java.util.*;

public class ProductService implements IGenericService<Product, String>{
    List<Product> productList= new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
//    public Product findById(String id) {
//        Optional<Product> product= productList.stream().filter(p->p.getProductId().equals(id)).findFirst();
//        return null;
//    }
    public Product findById(String productId) {
        // Assuming products are stored in a list
        return productList.stream()
                .filter(p -> p.getProductId().equalsIgnoreCase(productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        productList.removeIf(p->p.getProductId().equals(id));
    }

    public void sortByPriceDescending() {
        productList.sort(Comparator.comparingDouble(Product::getProductPrice).reversed());
    }

    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return result; // Return empty list if search name is null or empty
        }
        String lowerCaseName = name.toLowerCase();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(lowerCaseName)) {
                result.add(product);
            }
        }
        return result;
    }




}