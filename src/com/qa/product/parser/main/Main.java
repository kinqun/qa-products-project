package com.qa.product.parser.main;

import java.util.ArrayList;

import com.qa.product.parser.bean.Product;
import com.qa.product.parser.exceptions.ProductNotFoundException;
import com.qa.product.parser.service.ProductService;

public class Main {
	public static void main(String[] args) {
		ProductService prodService = new ProductService();
		ArrayList<Product> prodsList = prodService.getProductsList();
		
		
		prodService.showProductsInfo(prodsList);
		System.out.println("Product List Length: " + prodsList.stream().count() + " \n");
		
		Product highestPricedProduct = prodService.getHighestPricedProduct(prodsList);
		System.out.println("Highest Priced Product: " + highestPricedProduct.getName() + " - " + prodService.formatPrice(highestPricedProduct.getPrice()));
		
		Product lowestPricedProduct = prodService.getLowestPricedProduct(prodsList);
		System.out.println("Lowest Priced Product: " + prodService.formatPrice(prodService.getFinalPrice(lowestPricedProduct)));
		
		Product highestRatedProduct = prodService.highestRatedProduct(prodsList);
		System.out.println("Highest Rated Product: " + highestRatedProduct.getName() + " - " + highestRatedProduct.getRating() + "/5.0");
				
		
		try {
			System.out.println("\n----------------------------------\nfilter by category\n------------------------------");
			prodService.showProductsByCategory("Stationaryyyyyyy", prodsList);
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
