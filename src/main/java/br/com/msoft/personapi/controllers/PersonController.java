package br.com.msoft.personapi.controllers;


import br.com.msoft.personapi.dto.response.MessageResponseDTO;
import br.com.msoft.personapi.entities.Person;
import br.com.msoft.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Create person with id "+ savePerson.getId())
                .build();
    }
}
