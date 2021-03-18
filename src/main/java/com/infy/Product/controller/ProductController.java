package com.infy.Product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.StockDTO;
import com.infy.Product.Dto.SubscribedproductDTO;
import com.infy.Product.service.ProductMSException;
import com.infy.Product.service.ProductService;



@RestController
@CrossOrigin
public class ProductController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Environment environment;
	
	@Value("${user.uri}")
	String userUri;
	
	
	
	
	
	// To Get all products
	@GetMapping(value = "/api/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		logger.info("Fetching all products");
		System.out.println("In controller");
		return productService.getAllProducts();
	}
	
	
	
	
	
	
	//To get specific product details using prod id
	@GetMapping(value = "/api/products/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductsDetails(@PathVariable Integer prodid) throws ProductMSException{
		logger.info("fetching  Product according to product id {}", prodid);
		return productService.getProductById(prodid);
	}
	
	
	
	
	
	
	
	// Get specific products based on category
	@GetMapping(value = "/api/{category}/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByCategory(@PathVariable String category) throws ProductMSException{
		logger.info("Fetching Product according to category {}", category);
		return productService.getProductByCategory(category);
	}
	
	
	
	
	
	
	// Get Specific products based on product name
	@GetMapping(value = "/api/productname/{productname}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByName(@PathVariable String productname) throws ProductMSException{
		logger.info("Fetching Products according  to product name {}", productname);
		return productService.getProductByName(productname);
	}
	
	
	
	
	
	
	
	
	//Add product to the database
	@PostMapping(value = "/api/product/add",  produces = MediaType.APPLICATION_JSON_VALUE)
		public void addProduct(@RequestBody ProductDTO productDTO) throws ProductMSException {
			
			productService.addProduct(productDTO);
		}

    
	

	
	
	
    //Get Subscriptions
	@GetMapping(value = "/api/subscriptions/{subid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public SubscribedproductDTO getSubscriptionsDetails(@PathVariable Integer subid) throws ProductMSException{
		logger.info("fetching details of Product with product id {}", subid);
		return productService.getDetailsBysubId(subid);
	}
	
	
	
	
	
	
	    //Stock updation
		@PutMapping(value = "/api/stock/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> Stock(@RequestBody StockDTO stockDTO) throws ProductMSException {
			ResponseEntity<String> response = null;
			try {
				boolean flag=productService.Stock(stockDTO);
				if(flag) {
			String msg=environment.getProperty("STOCK_UPDATED");
			 response=new ResponseEntity<String>(msg,HttpStatus.OK);
			}
				else
				{
					logger.info("Update Stock {}",stockDTO.getStock());
					productService.Stock(stockDTO);
					String failureMessage=environment.getProperty("LESS_QUANTITY");
					response=new  ResponseEntity<String>(failureMessage,HttpStatus.BAD_REQUEST);
				}
			}
			catch(Exception e) {
				throw new ResponseStatusException (HttpStatus.OK,environment.getProperty(e.getMessage()),e);
				
			}
			return response;
		}
		
		
		
	
		//Update stock after order
		@PutMapping(value = "/api/order/update/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> updateStockAfterOrder(@PathVariable Integer prodid,@RequestBody Integer Quantity) throws ProductMSException {
			ResponseEntity<String> response;
			try{
				
				boolean flag=productService.updateStockAfterOrder(prodid,Quantity);
				if(flag) {
				String msg=environment.getProperty("Update_stock");
				 response=new ResponseEntity<String>(msg,HttpStatus.OK);
				}
				else {
					String failureMessage=environment.getProperty("CANNOT_UPDATE");
					response=new  ResponseEntity<String>(failureMessage,HttpStatus.BAD_REQUEST);
				}
					
				}
				catch(Exception e) {
					ResponseStatusException exception=new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
					throw exception;
				}
		return response;	
		}
		
		
		
		
		
		
		
		//Check stock and get quantity if less than stock-quantity
		@GetMapping(value="/api/order/checkstock/{prodid}/{Quantity}")
         public ResponseEntity<String> checkStockBeforeOrder(@PathVariable Integer prodid,@PathVariable Integer Quantity) throws Exception {
			ResponseEntity<String> response;
			try{
				
				boolean flag=productService.checkStockBeforeOrder(prodid, Quantity);
				if(flag) {
				String msg=environment.getProperty("PRESENT");
				 response=new ResponseEntity<String>(msg,HttpStatus.OK);
				}
				else {
					String failureMessage=environment.getProperty("LESS_QUANTITY");
					response=new  ResponseEntity<String>(failureMessage,HttpStatus.BAD_REQUEST);
				}
					
				}
				catch(Exception e) {
					ResponseStatusException exception=new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
					throw exception;
				}
		return response;
		
		
		}
	
		
	
}