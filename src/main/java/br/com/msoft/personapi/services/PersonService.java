package br.com.msoft.personapi.services;

import br.com.msoft.personapi.dto.mapper.PersonMapper;
import br.com.msoft.personapi.dto.request.PersonDTO;
import br.com.msoft.personapi.dto.response.MessageResponseDTO;
import br.com.msoft.personapi.entities.Person;
import br.com.msoft.personapi.exception.PersonNotFoundException;
import br.com.msoft.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savePerson = personRepository.save(personToSave);
        return createMessageResponse(savePerson, "Create person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person>allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

//    public PersonDTO findById(Long id) throws PersonNotFoundException {
//        Optional<Person> optionalPerson = personRepository.findById(id);
//        if(optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id);
//        }
//        return personMapper.toDTO(optionalPerson.get());
//    }

    //Refatorando o Metodo
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists (Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson, "Update person with ID ");
    }

    private MessageResponseDTO createMessageResponse(Person savePerson, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + savePerson.getId())
                .build();
    }
}
