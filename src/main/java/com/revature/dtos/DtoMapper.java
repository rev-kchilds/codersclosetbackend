package com.revature.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.revature.models.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DtoMapper {
    
    private ModelMapper modelMapper = new ModelMapper();

    public ProductDto convertProductToDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

    public Product convertDtoToProduct(ProductDto pdto){
        return modelMapper.map(pdto, Product.class);
    }
}
