package com.pedidos.teste.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pedidos.teste.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("from Pedido p where p.dataCadastro = :data")
	List<Pedido> findByData(@Param("data") String data);

}
