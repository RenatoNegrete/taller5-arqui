package com.example.demo.dal;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CompraRequest;
import com.example.demo.dto.CompraResponse;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.model.Factura;
import com.example.demo.model.Pago;
import com.example.demo.model.Producto;
import com.example.demo.repository.facturacion.FacturaRepository;
import com.example.demo.repository.inventario.ProductoRepository;
import com.example.demo.repository.pagos.PagoRepository;

import jakarta.transaction.Transactional;

@Component
public class DAL {
    private final FacturaRepository facturaRepository;
    private final PagoRepository pagoRepository;
    private final ProductoRepository productoRepository;

    public DAL(FacturaRepository facturaRepository, PagoRepository pagoRepository, ProductoRepository productoRepository) {
        this.facturaRepository = facturaRepository;
        this.pagoRepository = pagoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public CompraResponse procesarCompra(CompraRequest request) {
        // Calcular total
        double total = 0;
        for (ProductoDTO item : request.getProductos()) {
            Producto p = productoRepository.findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            if (p.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + p.getNombre());
            }
            total += item.getCantidad() * 100;
            p.setStock(p.getStock() - item.getCantidad());
            productoRepository.save(p);
        }

        // Crear factura
        Factura factura = new Factura();
        factura.setCliente(request.getCliente());
        factura.setTotal(total);
        factura.setFecha(request.getFecha());
        facturaRepository.save(factura);

        // Crear pago
        Pago pago = new Pago();
        pago.setMetodo(request.getMetodo());
        pago.setMonto(total);
        pago.setFecha(request.getFecha());
        pagoRepository.save(pago);

        // Respuesta
        return new CompraResponse("Compra exitosa", factura.getId(), pago.getId(), total);
    }
}
