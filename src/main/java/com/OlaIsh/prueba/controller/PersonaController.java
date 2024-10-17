package com.OlaIsh.prueba.controller;

import com.OlaIsh.prueba.entities.Persona;
import com.OlaIsh.prueba.persistence.repository.PersonalRepository;
import com.OlaIsh.prueba.persistence.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Para hacer peticiones desde otro servidor
@RestController // Para hacer peticiones REST
@RequestMapping("/people")
public class PersonaController {

    @Autowired
    private PersonalRepository invitadoRepository;

    @PostMapping("/")
    public void createPeople(@RequestBody Persona person) {
        invitadoRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePeople(@PathVariable("id") Integer id) {
        Persona i = new Persona();
        i.setId(id);
        invitadoRepository.delete(i);
    }

    @GetMapping
    public List<Persona> selectPeople() {
        List<Persona> people = invitadoRepository.findAll();
        return people;
    }

    @PutMapping("/{id}")
    public void updatePeople(@RequestBody Persona person, @PathVariable("id")Integer id) {
        person.setId(id);
        invitadoRepository.save(person);
    }
}