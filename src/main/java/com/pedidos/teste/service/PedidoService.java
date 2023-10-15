package com.pedidos.teste.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.teste.entities.Pedido;
import com.pedidos.teste.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Optional<Pedido> findById(Long id) {
		return pedidoRepository.findById(id);
	}

	public Pedido insert(Pedido pedido) throws Exception {
		validaPedido(pedido);
		return pedidoRepository.save(pedido);
	}

	public List<Pedido> insertList(List<Pedido> pedido) throws Exception {
		List<Pedido> result = new ArrayList();
		if (pedido.size() <= 10) {
			result = pedidoRepository.saveAll(pedido);
		} else {
			throw new Exception("Apenas podem ser salvos 10 pedidos");
		}
		
		return result;
	}

	public List<Pedido> findByData(LocalDateTime data) {
		return pedidoRepository.findByData(data);
	}

	private void validaPedido(Pedido pedido) throws Exception {
		if (verficarIdExistente(pedido) == true) {
			if (pedido.getQuantidadeProduto() == null) {
				pedido.setQuantidadeProduto(1);
			}

			if (pedido.getDataCadastro() == null) {
				pedido.setDataCadastro(LocalDateTime.now());
			}

			validaDesconto(pedido);

		} else {
			throw new Exception("O id informado já existe no banco de dados");
		}
	}

	private void validaDesconto(Pedido pedido) {
		if (pedido.getQuantidadeProduto() < 10 && pedido.getQuantidadeProduto() >= 5) {
			double descontos = (pedido.getValorProduto() * 5) / 100;
			pedido.setValorProduto(pedido.getValorProduto() - descontos);
		} else if (pedido.getQuantidadeProduto() >= 10) {
			double descontos = (pedido.getValorProduto() * 10) / 100;
			pedido.setValorProduto(pedido.getValorProduto() - descontos);
		}
	}

	private boolean verficarIdExistente(Pedido pedido) {
		Optional<Pedido> resultado = pedidoRepository.findById(pedido.getNumeroControle());
		if (resultado.isEmpty()) {
			return true;
		}
		return false;
	}

}
