package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.revature.dtos.DtoMapper;
import com.revature.dtos.ProductDto;
import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;

class ProductControllerTest {

    HttpSession session = Mockito.mock(HttpSession.class);
    ProductService productService = Mockito.mock(ProductService.class);
    ProductController productController = new ProductController(productService);
    @MockBean
    DtoMapper dtoConv;
    // Product Mock Objects
    Product p1 = new Product(1, 15, 40.00, "Special coat", "coat.png", "Special Coat v2");
    Product p2 = new Product(2, 45, 13.00, "Magic Socks", "socks.png", "Magic Socks");
    Product p3 = new Product(3, 4, 370.00, "Iron T-Shirt", "tshirt.png", "Iron plate T-Shirt");
    List<Product> productList = new ArrayList<Product>(){
        {
            add(p1);
            add(p2);
            add(p3);
        }
    };
    ArrayList<ProductInfo> metadata = new ArrayList<ProductInfo>(){
        {
            add(new ProductInfo(1,3));
            add(new ProductInfo(2,15));
            add(new ProductInfo(3,1));
        }
    };

    @Test
    void testDeleteProductSuccess() {
        // arrange
        Integer productId = 1;
        Product product = p1;
        Mockito.when(productService.findById(productId)).thenReturn(Optional.of(product));
        // act & assert
        assertEquals(ResponseEntity.ok(product), productController.deleteProduct(productId));
        // assert-verify
        Mockito.verify(productService, Mockito.times(1)).delete(productId);
    }

    @Test
    void testDeleteProductFail() {
        // arrange
        Integer productId = 1;
        Mockito.when(productService.findById(productId)).thenReturn(Optional.empty());
        // act & assert
        assertEquals(ResponseEntity.notFound().build(), productController.deleteProduct(productId));
    }

    @Test
    void testGetInventory() {
        // arrange
        List<Product> product_list_response = new ArrayList<Product>();
        Mockito.when(productService.findAll()).thenReturn(product_list_response);
        // act & assert
        assertEquals(ResponseEntity.ok(product_list_response), productController.getInventory());
    }

    @Test
    void testGetProductByIdSuccess() {
        // arrange
        Integer productId = 1;
        Product product = p1;
        Mockito.when(productService.findById(productId)).thenReturn(Optional.of(product));
        // act & assert
        assertEquals(ResponseEntity.ok(product), productController.getProductById(productId));
    }

    @Test
    void testGetProductByIdFail(){
        // arrange
        Integer productId = 1;
        Mockito.when(productService.findById(productId)).thenReturn(Optional.empty());
        // act & assert
        assertEquals(ResponseEntity.notFound().build(), productController.getProductById(productId));
    }

    @Test
    void testPurchaseProductsFoundAndSaved() {
        // arrange
        ProductInfo currentMeta;
        Integer currentId;
        for(int i = 0; i < metadata.size(); i++){
            currentMeta = metadata.get(i);
            currentId = metadata.get(i).getId();
            Mockito.when(productService.findById(currentId)).thenReturn(Optional.of(productList.get(i)));
            assertEquals(Optional.of(productList.get(i)), productService.findById(currentId));
            Optional<Product> p = productService.findById(currentId);
            assertTrue(p.get().getQuantity() - currentMeta.getQuantity() > 0);
        }
        Mockito.when(productService.saveAll(productList, metadata)).thenReturn(productList);
        assertEquals(productList, productService.saveAll(productList, metadata));  
    }

    @Test
    void testPurchaseWithProductNotFound() {
        //arrange
        Integer id = 0;
        Integer quantity = 5;
        List<ProductInfo> metadata = new ArrayList<>();
        metadata.add(new ProductInfo(id, quantity));
        ResponseEntity<List<Product>> expected = ResponseEntity.notFound().build();
        Mockito.when(productService.findById(metadata.get(0).getId())).thenReturn(Optional.empty());
        //act
        ResponseEntity<List<Product>> actual = productController.purchase(metadata);
        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPurchaseWithPurchaseQuantityTooHigh() {
        //arrange
        Integer id = 0;
        Integer quantity = 5;
        List<ProductInfo> metadata = new ArrayList<>();
        metadata.add(new ProductInfo(id, quantity));
        ResponseEntity<List<Product>> expected = ResponseEntity.badRequest().build();
        Mockito.when(productService.findById(metadata.get(0).getId())).thenReturn(Optional.of(new Product(0, 4, 0, "description", "image", "name")));
        //act
        ResponseEntity<List<Product>> actual = productController.purchase(metadata);
        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testValidPurchaseOfValidItem() {
        //arrange
        Integer id = 0;
        Integer quantity = 5;
        List<ProductInfo> metadata = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        metadata.add(new ProductInfo(id, quantity));
        Mockito.when(productService.findById(metadata.get(0).getId())).thenReturn(Optional.of(new Product(0, 8, 0, "description", "image", "name")));
        products.add(new Product(0, 3, 0, "description", "image", "name"));
        Mockito.when(productService.saveAll(products, metadata)).thenReturn(products);
        ResponseEntity<List<Product>> expected = ResponseEntity.ok(products);
        //act
        ResponseEntity<List<Product>> actual = productController.purchase(metadata);
        //assert
        Assertions.assertEquals(expected, actual);
    }
}
