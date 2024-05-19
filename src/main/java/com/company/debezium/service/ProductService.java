package com.company.debezium.service;

import com.company.debezium.model.Product;
import com.company.debezium.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long productId, Product updateProduct) {
        final Product currentProduct = productRepository.findById(productId).orElse(null);
        currentProduct.setName(updateProduct.getName());
        currentProduct.setPrice(updateProduct.getPrice());
        currentProduct.setStock(updateProduct.getStock());
        return productRepository.save(currentProduct);
    }
}
