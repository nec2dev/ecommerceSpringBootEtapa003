package com.nec2solutions.ecommercespringbootetapas.repository;
import com.nec2solutions.ecommercespringbootetapas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
