package com.nec2solutions.ecommercespringbootetapas.service;
import com.nec2solutions.ecommercespringbootetapas.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findById(Integer id);
}
