package com.nec2solutions.ecommercespringbootetapas.service;

import com.nec2solutions.ecommercespringbootetapas.model.Usuario;
import com.nec2solutions.ecommercespringbootetapas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioImplement implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
}

