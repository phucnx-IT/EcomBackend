package cybersoft.java16.ecom.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.dto.ProductReturnDTO;
import cybersoft.java16.ecom.product.dto.ProductUpdateDTO;
import cybersoft.java16.ecom.product.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping
	public Object createNewProduct(@Valid @RequestBody ProductDTO dto
							, BindingResult result ) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		
		ProductDTO newProduct = service.createNewProduct(dto);
		return ResponseHelper.getResponse(newProduct, HttpStatus.OK);
	}
	
	@GetMapping
	public Object findAllProductDTO() {
		List<ProductReturnDTO> productsDTO = service.findAllProductDTO(); 
		return ResponseHelper.getResponse(productsDTO, HttpStatus.OK);	
	}
	
	@GetMapping("/id/{product-id}")
	public Object findById(@PathVariable("product-id") String id ) {
		ProductDTO product = service.findById(id);
		if(product == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(product, HttpStatus.OK);
	}
	
	@GetMapping("/name/{product-name}")
	public Object findByName(@PathVariable("product-name") String name) {
		ProductDTO product = service.findByName(name);
		if(product == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(product, HttpStatus.OK);
	}
	
	@PutMapping("/{product-id}")
	public Object updateProduct(@PathVariable("product-id") String id
								,@Valid @RequestBody ProductUpdateDTO dto
								,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		ProductUpdateDTO updatedProduct = service.updateProduct(id, dto);
		
		if(updatedProduct == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}	
		return ResponseHelper.getResponse(updatedProduct, HttpStatus.OK);
	}
	
	@PostMapping("/add-size/{product-id}/{size-id}")
	public Object addSize(@PathVariable("product-id") String productId,
							@PathVariable("size-id") String sizeId) {
		ProductReturnDTO productReturnDTO = service.addSize(productId, sizeId);	
		if(productReturnDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(productReturnDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{product-id}")
	public Object deleteProduct(@PathVariable("product-id") String id) {
		ProductDTO deleteProduct = service.deleteProductById(id);
		if(deleteProduct == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHelper.getResponse(deleteProduct, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-size/{product-id}/{size-id}")
	public Object removeSize(@PathVariable("product-id") String productId,
								@PathVariable("size-id") String sizeId) {
		ProductReturnDTO modifiedProductReturnDTO = service.removeSize(productId, sizeId);
		if(modifiedProductReturnDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(modifiedProductReturnDTO, HttpStatus.OK);
	}
}
