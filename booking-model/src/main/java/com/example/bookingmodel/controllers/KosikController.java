package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.KosikDTO;
import com.example.bookingmodel.data.dto.ProductDTO;
import com.example.bookingmodel.services.KosikService;
import com.example.bookingmodel.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController("kosikController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/kosik")
public class KosikController {

    private final ProductService productService;

    @PostMapping("/add/{productId}")
    public void addProductToKosik(@PathVariable int productId, HttpSession session) {
        List<Integer> kosik = (List<Integer>) session.getAttribute("kosik");
        if (kosik == null) {
            kosik = new ArrayList<>();
        }
        kosik.add(productId);
        session.setAttribute("kosik", kosik);
    }

    @DeleteMapping("/remove/{productId}")
    public void removeProductFromKosik(@PathVariable int productId, HttpSession session) {
        List<Integer> kosik = (List<Integer>) session.getAttribute("kosik");
        if (kosik != null) {
            kosik.remove(Integer.valueOf(productId));
            session.setAttribute("kosik", kosik);
        }
    }

    @GetMapping("/get")
    public List<String> getKosik(HttpSession session) {
        List<Integer> kosik = (List<Integer>) session.getAttribute("kosik");
        List<String> productNames = new ArrayList<>();

        if (kosik != null) {
            for (Integer productId : kosik) {
                ProductDTO product = productService.getProductById(productId);
                productNames.add(product.getNazev());
            }
        }

        return productNames;
    }
}
