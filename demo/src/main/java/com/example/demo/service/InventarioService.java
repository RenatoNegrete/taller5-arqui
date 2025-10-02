package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class InventarioService {
    private final ProductoRepository productoRepo;

    public InventarioService(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    public void actualizarCantidad(Long id, int nuevaCantidad) {
        Producto p = productoRepo.findById(id).orElseThrow();
        p.setStock(nuevaCantidad);
        productoRepo.save(p);
    }
}
