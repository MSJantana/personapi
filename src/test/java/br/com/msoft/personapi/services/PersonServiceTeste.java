package br.com.msoft.personapi.services;

import br.com.msoft.personapi.dto.mapper.PersonMapper;
import br.com.msoft.personapi.dto.request.PersonDTO;
import br.com.msoft.personapi.dto.response.MessageResponseDTO;
import br.com.msoft.personapi.entities.Person;
import br.com.msoft.personapi.repository.PersonRepository;
import br.com.msoft.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.msoft.personapi.utils.PersonUtils.createFakeEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTeste {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnPersonEntity(){
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavePerson = createFakeEntity();

        Mockito.when(personMapper.toModel(personDTO)).thenReturn(expectedSavePerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavePerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavePerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage,successMessage);

    }

    private MessageResponseDTO createExpectedSuccessMessage(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Person successfully created with ID" + id)
                .build();
    }

}
