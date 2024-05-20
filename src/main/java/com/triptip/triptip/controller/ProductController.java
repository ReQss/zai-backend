package com.triptip.triptip.controller;


import com.triptip.triptip.model.Category;
import com.triptip.triptip.model.Product;
import com.triptip.triptip.model.User;
import com.triptip.triptip.repository.CategoryRepository;
import com.triptip.triptip.repository.ProductRepository;
import com.triptip.triptip.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/product/listAll")
    public List<Product>listAll(){
        return productService.getAllProducts();
    }
    @PutMapping("/admin/setProduct/{id}")
    public void setProduct(@PathVariable int id){
        Product product = productRepository.findById(id);
        if(product == null)return ;
        product.setStock(0);
        productRepository.save(product);
    }
    @PostMapping("/admin/addProduct")
    public void addProduct(
            @RequestParam String productName,
            @RequestParam String imageSrc,
            @RequestParam int stock,
            @RequestParam float price,
            @RequestParam int platformId,
            @RequestParam int categoryId

    ){
        System.out.println(categoryId);
        Category category = categoryRepository.findById(categoryId);
        if(category==null)System.out.println("Not ofund");
        else {
            Product product = new Product(productName, imageSrc, stock, price, platformId, category);
            productRepository.save(product);
        }
    }
}
