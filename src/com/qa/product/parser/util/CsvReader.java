package com.qa.product.parser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.qa.product.parser.bean.Product;

public class CsvReader {
	private static final String PRODUCT_CSV_FILE_PATH = "C:\\JAVA_WORKSPACE\\qa\\product_parser_java\\src\\data\\products.csv";
	
	public ArrayList<Product> getProductsFromCsv(){
		ArrayList<Product> productsList = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File(PRODUCT_CSV_FILE_PATH)))){
			String productData = br.readLine(); // skip 1st row - headers;
			productsList = new ArrayList<Product>();
			
			while((productData = br.readLine()) != null) {
				
				try {
					String[] data = productData.split(",");
					
					Product currentProduct = new Product();
					currentProduct.setId(Integer.parseInt(data[0]))
						.setName(data[1])
						.setPrice(Float.parseFloat(data[2]))
						.setCategory(data[3])
						.setDiscountPercentage(Byte.parseByte(data[4]))
						.setAvailable(data[5].equals("yes") ? true : false)
						.setRating(Float.parseFloat(data[6]));

					productsList.add(currentProduct);
				}catch(NullPointerException e) {
					System.out.println(e.getMessage());;
				}
				
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());;
		}
		
		
		
		return productsList;
	}
}
