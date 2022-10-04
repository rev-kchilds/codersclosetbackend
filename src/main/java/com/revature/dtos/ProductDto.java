package com.revature.dtos;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductDto {
    private int id;
    private int quantity;
    private double price;
    private String description;
    private String image;
    private String name;
}
