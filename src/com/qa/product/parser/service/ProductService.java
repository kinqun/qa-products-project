package com.qa.product.parser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.qa.product.parser.bean.Product;
import com.qa.product.parser.exceptions.ProductNotFoundException;
import com.qa.product.parser.repository.ProductRepository;

public class ProductService {
	ProductRepository prodRepository;
	
	public ProductService() {
		this.prodRepository = new ProductRepository();
	}

	public ArrayList<Product> getProductsList(){
		
		return this.prodRepository.getProductsList();
	}
	
	public String formatPrice(float price) {
		return "$" + String.format("%.2f", price);
	}
	
	public float getDiscountAmount(Product product) {
		return product.getPrice() * product.getDiscountPercentage() / 100;
	}
	
	public float getFinalPrice(Product product) {
		return product.getPrice() - getDiscountAmount(product);
	}
	
	public void showProductsInfo(ArrayList<Product> products) {
		products.stream().forEach(p->{
			System.out.println("Product ID: " + p.getId());
			System.out.println("Product Name: " + p.getName());
			System.out.println("Product Category: " + p.getCategory());
			System.out.println("Product Price: " + formatPrice(p.getPrice()));
			System.out.println("Product Discount Amount: " + formatPrice(getDiscountAmount(p)));
			System.out.println("Product Final Price: " + formatPrice(getFinalPrice(p)));
			System.out.println("Product Rating: " + p.getRating() + "/5.0");
			System.out.println("Product Discount: " + p.getDiscountPercentage() + "%");
			System.out.println("Product Availability: " + (p.getAvailable() == true ? "Yes" : "No"));
			System.out.println("");
		});
	}
	public void showProductsInfo(Product product) {
		System.out.println("Product ID: " + product.getId());
		System.out.println("Product Name: " + product.getName());
		System.out.println("Product Category: " + product.getCategory());
		System.out.println("Product Price: " + formatPrice(product.getPrice()));
		System.out.println("Product Discount Amount: " + formatPrice(getDiscountAmount(product)));
		System.out.println("Product Final Price: " + formatPrice(getFinalPrice(product)));
		System.out.println("Product Rating: " + product.getRating() + "/5.0");
		System.out.println("Product Discount: " + product.getDiscountPercentage() + "%");
		System.out.println("Product Availability: " + (product.getAvailable() == true ? "Yes" : "No"));
		System.out.println("");
	}
	
	public Product getHighestPricedProduct(ArrayList<Product> products) {
		return products.stream()
				.sorted((a,b)->(int)getFinalPrice(a) - (int)getFinalPrice(b))
				.collect(Collectors.toList())
				.get(products.size()-1);
	}
	
	public Product getLowestPricedProduct(ArrayList<Product> products) {
		return products.stream()
				.sorted((a,b)->(int)getFinalPrice(a) - (int)getFinalPrice(b))
				.collect(Collectors.toList())
				.get(0);
	}
	
	public Product highestRatedProduct(ArrayList<Product> products) {
		return products.stream()
				.sorted((a,b)-> (int)(b.getRating() * 100) - (int)(a.getRating() * 100))
				.collect(Collectors.toList())
				.get(0);
	}
	
	public void showProductsByCategory(String category, ArrayList<Product> products) throws ProductNotFoundException{
		List<Product> prods = products.stream()
				.filter(prod->prod.getCategory().equals(category))
				.collect(Collectors.toList());
		
		if(prods.size() == 0) {
			throw new ProductNotFoundException("invalid category...");
		}
		
		prods.forEach(p->showProductsInfo(p));
	}
}
