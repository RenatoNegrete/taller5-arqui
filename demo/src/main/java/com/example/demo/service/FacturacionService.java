package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Factura;
import com.example.demo.repository.FacturaRepository;

@Service
public class FacturacionService {
    private final FacturaRepository facturaRepository;

    public FacturacionService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }
}
