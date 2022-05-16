package de.pimpMyMeal.pimpMyMeal.web;

import org.springframework.stereotype.Service;
import de.pimpMyMeal.pimpMyMeal.web.Product;
import de.pimpMyMeal.pimpMyMeal.web.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PMMService {
    private final ProductRepository productRepository;
    private final ProductTransformer productTransformer;

    public PMMService(ProductRepository productRepository, ProductTransformer productTransformer){
        this.productRepository = productRepository;
        this.productTransformer = productTransformer;
    }

    public List<Product> findAll(){

        List<Product> product = productRepository.findAll();
        return product.stream()
                .collect(Collectors.toList());
    }

    public Product create(PersonManipulationRequest request) {
        var product = new Product(request.getId(), request.getName(), request.getPrice());
        product = (Product) productRepository.save(product);
        return (Product) productTransformer.transformEntity(product);
    }

    public Product saveProduct(Product product){
        productRepository.save(product);
        return product;
    }

    public boolean deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }

    private class PersonManipulationRequest {
        public Object getId() {
            return null;
        }
        public Object getName() {
            return null;
        }
        public Object getPrice() {
            return null;
        }
    }
}
