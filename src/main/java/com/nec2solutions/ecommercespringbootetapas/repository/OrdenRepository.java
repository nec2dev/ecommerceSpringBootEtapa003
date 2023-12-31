package com.nec2solutions.ecommercespringbootetapas.repository;

import com.nec2solutions.ecommercespringbootetapas.model.Orden;
import com.nec2solutions.ecommercespringbootetapas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByUsuario (Usuario usuario);
}
