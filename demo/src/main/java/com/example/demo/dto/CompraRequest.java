package com.example.demo.dto;

import java.util.List;

public class CompraRequest {
    private String cliente;
    private List<ItemCompra> items;
    private String metodoPago;

    // getters y setters

    public static class ItemCompra {
        private Long productoId;
        private int cantidad;
        // getters y setters
    }
}
