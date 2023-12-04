package com.example.bookingmodel.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CategoryProduct {
    ELEKTRONIKA("Elektronika"),
    POTRAVINY("Potraviny"),
    OBLECENY("Obleceny"),
    DUM("Dum a zahrada"),
    KOSMETIKA("Kosmetika"),
    SPORT("Sport a volny cas"),
    KNIHA("Knihy a media");


    @Getter
    private final String categorie;
}
