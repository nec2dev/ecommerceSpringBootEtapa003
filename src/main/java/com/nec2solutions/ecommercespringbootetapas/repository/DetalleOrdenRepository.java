package com.nec2solutions.ecommercespringbootetapas.repository;

import com.nec2solutions.ecommercespringbootetapas.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

}
