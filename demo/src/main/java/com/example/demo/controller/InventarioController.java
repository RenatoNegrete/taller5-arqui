package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.service.InventarioService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return inventarioService.getProductos();
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<String> actualizarStock(@PathVariable Long id, @RequestParam int cantidad) {
        inventarioService.actualizarCantidad(id, cantidad);
        return ResponseEntity.ok("Stock actualizado");
    }

}
