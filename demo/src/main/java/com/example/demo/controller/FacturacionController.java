package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Factura;
import com.example.demo.service.FacturacionService;

@RestController
@RequestMapping("/api/facturas")
public class FacturacionController {
    private final FacturacionService facturacionService;

    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    @GetMapping
    public List<Factura> listarProductos() {
        return facturacionService.getFacturas();
    }

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturacionService.save(factura);
    }

}
