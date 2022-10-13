package com.qa.product.parser.repository;

import java.util.ArrayList;

import com.qa.product.parser.bean.Product;
import com.qa.product.parser.util.CsvReader;

public class ProductRepository {
	ArrayList<Product> productsList;

	public ProductRepository() {
		CsvReader csvReader = new CsvReader();
		
		this.productsList = csvReader.getProductsFromCsv();
	}
	
	public ArrayList<Product> getProductsList() {
		return this.productsList;
	}
	

}
