package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductNotfoundException;
import com.example.demo.model.Product;

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo=new HashMap<>();
    static List<Product> ob=new ArrayList<>();
    static {
    	Product honey =new Product();
    	honey.setId("1");
    	honey.setName("Honey");
    	
    	productRepo.put(honey.getId(),honey);
    	
    	Product almond =new Product();
    	almond.setId("2");
    	almond.setName("Almond");
    	
    	productRepo.put(almond.getId(),almond);
    	
    	ob.add(honey);
    	ob.add(almond);
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
    	return ob;
    }
	
    @GetMapping(value="/getproducts/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") String id){
    	if(!productRepo.containsKey(id))throw new ProductNotfoundException();
    	  Product p=productRepo.get(id);
    	  return new ResponseEntity<>("Product is received successfully" + p.getName(), HttpStatus.OK);
    }

	}
