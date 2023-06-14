package com.nec2solutions.ecommercespringbootetapas.service;

import com.nec2solutions.ecommercespringbootetapas.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public Producto create(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}
