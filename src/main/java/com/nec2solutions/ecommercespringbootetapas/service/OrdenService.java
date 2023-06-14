package com.nec2solutions.ecommercespringbootetapas.service;

import com.nec2solutions.ecommercespringbootetapas.model.Orden;
import com.nec2solutions.ecommercespringbootetapas.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface OrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save (Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (Usuario usuario);
}
