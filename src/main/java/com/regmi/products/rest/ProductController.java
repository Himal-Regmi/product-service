package com.regmi.products.rest;

import com.regmi.products.entity.Product;
import com.regmi.products.exception.ProductNotFoundException;
import com.regmi.products.exception.UnauthorizedException;
import com.regmi.products.rest.payload.PayloadMapper;
import com.regmi.products.rest.payload.request.ProductRequest;
import com.regmi.products.rest.payload.response.ProductResponse;
import com.regmi.products.security.CurrentUser;
import com.regmi.products.service.ProductImageService;
import com.regmi.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ProductImageService productImageService;

    private final PayloadMapper payloadMapper= new PayloadMapper();

    @GetMapping("/products")
    public List<ProductResponse> getProducts(){
        List<Product> products= productsService.getProducts();
        return payloadMapper.getProductResponses(products);
    }
    @GetMapping("/products/query")
    public List<ProductResponse> getProductsByName(@RequestParam(value="name",required =false)String name){
        List<Product> products= productsService.getProductsByName(name);

        return payloadMapper.getProductResponses(products);
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable int id){
        Product product= productsService.getProduct(id);
        if(product == null){
            throw new ProductNotFoundException("Product with id - "+id+" :not found.");
        }
        return payloadMapper.getProductResponse(product);
    }
    @PostMapping("/products")
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest productRequest) {
        Product product=payloadMapper.getProductFromRequest(productRequest);
        product.setId(0);
        CurrentUser user= (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        product.setUserId(user.getId());
        productsService.save(product);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponse> patchProduct(@RequestBody ProductRequest productRequest, @PathVariable int id){
        CurrentUser currentuser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product =productsService.getProduct(id);
        if(currentuser.getId()!=product.getUserId()){
            throw new UnauthorizedException("Not Authorized for id-"+id);
        }
        if(productRequest.getName()!=null){
            product.setName(productRequest.getName());
        }
        if(productRequest.getCategory()!=null){
            product.setCategory(productRequest.getCategory());
        }
        if(productRequest.getDescription()!=null){
            product.setDescription(productRequest.getDescription());
        }
        if(productRequest.getPrice()!=0) {
            product.setPrice(productRequest.getPrice());
        }

        productsService.update(product);

        return ResponseEntity.ok(payloadMapper.getProductResponse(product));
    }
    @PatchMapping("/products/{id}/images")
    public void addImagesToProduct(@RequestBody List<String> imageUrls, @PathVariable int id){
        CurrentUser currentuser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product =productsService.getProduct(id);
        if(currentuser.getId()!=product.getUserId()){
            throw new UnauthorizedException("Not Authorized for id-"+id);
        }
        product.addImageUrl(imageUrls);

        productsService.update(product);

    }
    @DeleteMapping("/products/{id}/images/{imageId}")
    public void DeleteProductImage(@PathVariable int id,@PathVariable int imageId){
        CurrentUser currentuser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product =productsService.getProduct(id);
        if(currentuser.getId()!=product.getUserId()){
            throw new UnauthorizedException("Not Authorized for id-"+id);
        }
        productImageService.deleteProductImage(imageId);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id){

        if(productsService.getProduct(id) == null){
            throw new ProductNotFoundException("Product with id- "+id+" : not found.");
        }
        productsService.deleteProduct(id);

    }
}
