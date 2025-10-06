package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CompraRequest;
import com.example.demo.dto.CompraResponse;
import com.example.demo.dal.DAL;

@RestController
@RequestMapping("/api/compra")
public class CompraController {
    private final DAL DAL;

    public CompraController(DAL DAL) {
        this.DAL = DAL;
    }

    @PostMapping
    public CompraResponse realizarCompra(@RequestBody CompraRequest request) {
        return DAL.procesarCompra(request);
    }
}
