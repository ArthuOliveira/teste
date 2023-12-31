package com.pedidos.teste.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.teste.entities.Pedido;
import com.pedidos.teste.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public List<Pedido> findAll() {
		return pedidoService.findAll();
	}
	
	@GetMapping(value = "/busca/{id}")
	public Pedido findById(@PathVariable Long id) {
		return pedidoService.findById(id).get();
	}
	
	@GetMapping(value = "/data/{data}")
	public List<Pedido> findByData(@PathVariable LocalDateTime data) {
		return pedidoService.findByData(data);
	}
	
	@PostMapping
	public Pedido insert(@RequestBody Pedido pedido) throws Exception {
		return pedidoService.insert(pedido);
	}
	
	@PostMapping(value = "/salvar-lista")
	public List<Pedido> insertList(@RequestBody List<Pedido> pedido) throws Exception {
		return pedidoService.insertList(pedido);
	}

}
