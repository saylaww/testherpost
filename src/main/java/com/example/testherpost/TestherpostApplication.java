package com.example.testherpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class TestherpostApplication {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> getList(){
        List<Product> all = productRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Integer id){
        Optional<Product> byId = productRepository.findById(id);
        return byId.get();
    }

    @PostMapping("/add")
    public Product add(@RequestBody ProductDto productDto){
        try {
            Product save = productRepository.save(new Product(
                    productDto.getName()
            ));
            return save;
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TestherpostApplication.class, args);
    }

}
