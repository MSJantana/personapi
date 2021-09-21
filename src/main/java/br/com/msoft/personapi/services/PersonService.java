package br.com.msoft.personapi.services;

import br.com.msoft.personapi.dto.mapper.PersonMapper;
import br.com.msoft.personapi.dto.request.PersonDTO;
import br.com.msoft.personapi.dto.response.MessageResponseDTO;
import br.com.msoft.personapi.entities.Person;
import br.com.msoft.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;
    private Object PersonDTO;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Create person with ID "+ savePerson.getId())
                .build();
    }
}
