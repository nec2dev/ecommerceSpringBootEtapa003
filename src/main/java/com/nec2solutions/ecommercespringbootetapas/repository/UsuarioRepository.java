package com.nec2solutions.ecommercespringbootetapas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nec2solutions.ecommercespringbootetapas.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
