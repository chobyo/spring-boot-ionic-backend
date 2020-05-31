package com.chobyo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chobyo.cursomc.domains.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
