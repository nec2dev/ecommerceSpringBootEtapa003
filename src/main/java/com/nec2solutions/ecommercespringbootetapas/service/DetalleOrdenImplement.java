package com.nec2solutions.ecommercespringbootetapas.service;

import com.nec2solutions.ecommercespringbootetapas.model.DetalleOrden;
import com.nec2solutions.ecommercespringbootetapas.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenImplement implements DetalleOrdenService {
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
