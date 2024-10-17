package com.OlaIsh.prueba.persistence.repository;

import com.OlaIsh.prueba.entities.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonalRepository extends CrudRepository <Persona, Integer>{
    List<Persona> findAll();
}
