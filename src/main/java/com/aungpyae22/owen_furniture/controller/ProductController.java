package com.aungpyae22.owen_furniture.controller;

import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.exception.OurException;
import com.aungpyae22.owen_furniture.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/owen/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addProduct (@RequestParam(value = "productType", required = false) String productType,
                                                @RequestParam(value = "productName", required = false)  String productName,
                                                @RequestParam(value = "productPrice", required = false)  BigDecimal productPrice,
                                                @RequestParam(value = "productPhotoUrl", required = false)  String productPhotoUrl,
                                                @RequestParam(value = "productDescription", required = false)  String productDescription,
                                                @RequestParam(value = "availableProduct", required = false)  String availableProduct){

        if(productType.isBlank() || productName.isBlank() || productPrice == null || productPhotoUrl.isBlank() || productDescription.isBlank() || availableProduct.isBlank()){
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please fill the requirement of the field and correctly.");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        Response response = productService.addProduct(  productType,
                                                        productName,
                                                        productPrice,
                                                        productPhotoUrl,
                                                        productDescription,
                                                        availableProduct);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping(path = "/all")
    public ResponseEntity<Response> getAllProducts(){
        Response response = productService.getAllProduct();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(path = "/type")
    public ResponseEntity<Response> getAllProductType(){
        Response response = productService.getAllProduct();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(path = "/get-by-id/{productId}")
    public ResponseEntity<Response> getProductById(@PathVariable("ProductId") long productId){
        Response response = productService.getProductById(productId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(path = "/available-by-type")
    public ResponseEntity<Response> availableProductByType(@RequestParam(value = "productType" , required = false) String productType){

        if(productType == null){
            throw new OurException("product type field is require.");
        }

        Response response = productService.getAllAvailableProductByType(productType);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(path = "/all-available-products")
    public ResponseEntity<Response> allAvailableProducts(){
        Response response = productService.getAllAvailableProducts();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping(path = "/update/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProductById(@PathVariable("productId") long productId,
                                                      @RequestParam(value = "productType", required = false) String productType,
                                                      @RequestParam(value = "productName", required = false)  String productName,
                                                      @RequestParam(value = "productPrice", required = false)  BigDecimal productPrice,
                                                      @RequestParam(value = "productPhotoUrl", required = false)  String productPhotoUrl,
                                                      @RequestParam(value = "productDescription", required = false)  String productDescription,
                                                      @RequestParam(value = "availableProduct", required = false)  String availableProduct){

        if(productType.isBlank() || productName.isBlank() || productPrice == null || productPhotoUrl.isBlank() || productDescription.isBlank() || availableProduct.isBlank()){
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please fill the requirement of the field and correctly.");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        Response response = productService.updateProduct(productId, productType, productName, productPrice, productPhotoUrl, productDescription,availableProduct);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping(path = "/delete/{productId}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("productId") long productId){
        Response response = productService.deleteProduct(productId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
