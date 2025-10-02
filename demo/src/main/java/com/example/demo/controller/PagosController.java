package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pago;
import com.example.demo.service.PagosService;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {
    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @GetMapping
    public List<Pago> listarProductos() {
        return pagosService.getPagos();
    }

    @PostMapping
    public Pago createFactura(@RequestBody Pago pago) {
        return pagosService.save(pago);
    }

}
