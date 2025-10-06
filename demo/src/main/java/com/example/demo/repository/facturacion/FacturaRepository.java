package com.example.demo.repository.facturacion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
