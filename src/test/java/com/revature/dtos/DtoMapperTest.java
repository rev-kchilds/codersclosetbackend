package com.revature.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.revature.models.Product;

class DtoMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    @Test
    void testConvertProductDtoToModel() {
        // arrange
        ProductDto pdto = new ProductDto(1, 15, 40.00, "description", "image", "product");
        Product product = modelMapper.map(pdto, Product.class);
        // act & assert
        assertEquals(pdto.getId(), product.getId());
        assertEquals(pdto.getQuantity(), product.getQuantity());
        assertEquals(pdto.getPrice(), product.getPrice());
        assertEquals(pdto.getDescription(), product.getDescription());
        assertEquals(pdto.getImage(), product.getImage());
        assertEquals(pdto.getName(), product.getName());
    }

    @Test
    void testConvertProductToDto() {
        // arrange
        Product product = new Product(1, 15, 40.00, "description", "image", "product");
        ProductDto pdto = modelMapper.map(product, ProductDto.class);
        // act & assert
        assertEquals(product.getId(), pdto.getId());
        assertEquals(product.getQuantity(), pdto.getQuantity());
        assertEquals(product.getPrice(), pdto.getPrice());
        assertEquals(product.getDescription(), pdto.getDescription());
        assertEquals(product.getImage(), pdto.getImage());
        assertEquals(product.getName(), pdto.getName());
    }
}
