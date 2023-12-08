package com.example.bookingmodel.data.dto;

import com.example.bookingmodel.enums.CategoryProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private int id;

    @NotEmpty
    private String nazev;

    @NotEmpty
    private String popis;

    @NotEmpty
    private String vyrobce;

    @NotEmpty
    private double cena;

    @NotEmpty
    private int mnozstvi;

//    private byte[] image;

    @NotEmpty
    private CategoryProduct category;

}
