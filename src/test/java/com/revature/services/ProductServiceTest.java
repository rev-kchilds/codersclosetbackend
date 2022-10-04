package com.revature.services;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;

class ProductServiceTest {
    
    ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    ProductService productService = new ProductService(productRepository);
    // Mock Models
    Product p1 = new Product(1, 15, 40.00, "Special coat", "coat.png", "Special Coat v2");
    Product p2 = new Product(2, 45, 13.00, "Magic Socks", "socks.png", "Magic Socks");
    Product p3 = new Product(3, 4, 370.00, "Iron T-Shirt", "tshirt.png", "Iron plate T-Shirt");
    List<Product> list = Stream.of(p1, p2, p3).collect(Collectors.toCollection(ArrayList::new));
    List<ProductInfo> infolist = Stream.of(new ProductInfo(p1.getId(),p1.getQuantity()), new ProductInfo(p2.getId(),p2.getQuantity()), new ProductInfo(p3.getId(),p3.getQuantity())).collect(Collectors.toCollection(ArrayList::new));
    
    @Test
    void testFindAll(){
        // arrange
        Mockito.when(productRepository.findAll()).thenReturn(list);
        // act & assert
        Assertions.assertEquals(list, productService.findAll());
    }

    @Test
    void testFindByIdSuccess() {
        // arrange
        Integer productId = 1;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(p1));
        // act & assert
        Assertions.assertEquals(Optional.of(p1), productService.findById(productId));
    }

    @Test
    void testFindByIdFail(){
        // arrange
        Integer productId = 1;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());
        // act & assert
        Assertions.assertEquals(Optional.empty(), productService.findById(productId));
    }

    @Test
    void testSave() {
        //arrange
        Mockito.when(productRepository.save(p1)).thenReturn(p1);
        // act & assert
        Assertions.assertEquals(p1, productService.save(p1));
    }

    @Test
    void testSaveAll(){
        // arrange
        Mockito.when(productRepository.saveAll(list)).thenReturn(list);
        // act & assert
        Assertions.assertEquals(list, productService.saveAll(list, infolist));
    }

    @Test
    void testDelete(){
        // arrange
        Integer productId = 1;
        doNothing().when(productRepository).deleteById(productId);
        // act
        productService.delete(productId);
        // assert
        verify(productRepository, Mockito.times(1)).deleteById(productId);
    }
}
