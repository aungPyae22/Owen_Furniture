package com.aungpyae22.owen_furniture.service.impl;

import com.aungpyae22.owen_furniture.dto.ProductDTO;
import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.entity.Product;
import com.aungpyae22.owen_furniture.exception.OurException;
import com.aungpyae22.owen_furniture.repository.ProductRepository;
import com.aungpyae22.owen_furniture.service.interfaces.IProductService;
import com.aungpyae22.owen_furniture.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;


    @Override
    public Response addProduct(String productType, String productName, BigDecimal productPrice, String productPhotoUrl, String productDescription, String availableProduct) {
        Response  response = new Response();

        try{
            Product product = new Product();
            product.setProductType(productType);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            product.setProductPhotoUrl(productPhotoUrl);
            product.setProductDescription(productDescription);
            product.setAvailableProduct(availableProduct);

            Product saveProduct = repo.save(product);
            ProductDTO productDTO = Utils.mapProductEntityToProductDTO(saveProduct);

            response.setStatusCode(200);
            response.setMessage("successfully add product.");
            response.setProduct(productDTO);
        }
        catch(OurException e){
            response.setStatusCode(400);
            response.setMessage("Can't add product. "+ e.getMessage());
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during adding the product. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllProductType() {
        Response response = new Response();
        try{
            List<String> types = repo.findByDistinctProductType();
            response.setStatusCode(200);
            response.setMessage("successfully founded.");
            response.setProductTypes(types);
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Can't find the product's types. "+e.getMessage());
        }
        return response;

    }

    @Override
    public Response getAllProduct() {
        Response response = new Response();

        try{
            List<Product> productList = repo.findAll();
            List<ProductDTO> productDTOlist = Utils.mapProductListEntityToProductListDTO(productList);

            response.setStatusCode(200);
            response.setMessage("successfully got products");
            response.setProductList(productDTOlist);
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during getting all product. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableProductByType(String productType) {
        Response response = new Response();

        try{
            List<Product> productList = repo.findAvailableProductsByType(productType);
            List<ProductDTO> productDTOList = Utils.mapProductListEntityToProductListDTO(productList);

            response.setStatusCode(200);
            response.setMessage("successfully got available products by id");
            response.setProductList(productDTOList);
        }
        catch (OurException e){
            response.setStatusCode(400);
            response.setMessage("Can't find product's type. "+ e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during finding product's type. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableProducts() {
        Response response = new Response();

        try{
            List<Product> productList = repo.findAllAvailableProducts();
            List<ProductDTO> productDTOList = Utils.mapProductListEntityToProductListDTO(productList);

            response.setStatusCode(200);
            response.setMessage("successfully got all available products");
        }
        catch(Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurs during the getting all available products. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteProduct(Long id) {
        Response response = new Response();

        try{
            repo.findById(id).orElseThrow(() -> new OurException("Can't found the product"));
            repo.deleteById(id);

            response.setStatusCode(200);
            response.setMessage("successfully deleted product");
        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't found the product and can't delete. "+e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during the getting all available products. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateProduct(Long id, String productType, String productName, BigDecimal productPrice, String productPhotoUrl, String productDescription, String availableProduct) {
        Response response = new Response();

        try{
            Product product = repo.findById(id).orElseThrow(() -> new OurException("Can't find the product"));
            product.setProductType(productType);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            product.setProductPhotoUrl(productPhotoUrl);
            product.setProductDescription(productDescription);
            product.setAvailableProduct(availableProduct);

            ProductDTO productDTO = Utils.mapProductEntityToProductDTO(product);

            response.setStatusCode(200);
            response.setMessage("successfully updated.");
            response.setProduct(productDTO);
        }
        catch (OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't update product. "+e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during the updating product "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getProductById(Long id) {
        Response response = new Response();

        try{
            Product product = repo.findById(id).orElseThrow(() -> new OurException("Can't found the product."));
            ProductDTO productDTO = Utils.mapProductEntityToProductDTO(product);

            response.setStatusCode(200);
            response.setMessage("successfully got product by id");
            response.setProduct(productDTO);
        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't found the product by id. "+e.getMessage());
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during the getting product by id "+ e.getMessage());
        }
        return response;
    }
}
