package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pago;
import com.example.demo.repository.pagos.PagoRepository;

@Service
public class PagosService {
    private final PagoRepository pagoRepository;

    public PagosService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> getPagos() {
        return pagoRepository.findAll();
    }

    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

}
