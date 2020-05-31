package com.chobyo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chobyo.cursomc.domains.Cliente;
import com.chobyo.cursomc.repositories.ClienteRepository;
import com.chobyo.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteServico {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
