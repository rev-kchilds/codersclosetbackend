package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.revature.dtos.DtoMapper;
import com.revature.dtos.ProductDto;
import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;

@SpringBootTest
class ProductControllerUpsertMethodTest {
    // special wiring of Spring application context in order to use ModelMapper
    @Autowired
    ProductController productController;
    @MockBean
    DtoMapper dtoMapper;
    @MockBean
    ProductService productService;
    @MockBean
    ProductRepository productRepository;
    
    @Test
    void testUpsertFail() {
        // arrange
        ProductDto pdto = new ProductDto(1, 15, 40.00, "Special coat", "coat.png", "Special Coat v2");
        Product expected = dtoMapper.convertDtoToProduct(pdto);
        Mockito.when(productService.save(expected)).thenThrow(new NotLoggedInException("Must be logged in to perform this action"));
        // act
        NotLoggedInException thrown = assertThrows(NotLoggedInException.class, () -> productController.upsert(pdto));
        // assert
        assertTrue(thrown.getMessage().contains("Must be logged in to perform this action"));
    }
}
