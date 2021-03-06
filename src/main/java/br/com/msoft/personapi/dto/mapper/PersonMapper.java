package br.com.msoft.personapi.dto.mapper;

import br.com.msoft.personapi.dto.request.PersonDTO;
import br.com.msoft.personapi.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

  //PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  @Mapping(target = "birthDate",source = "birthDate", dateFormat = "dd-MM-yyyy")
  Person toModel(PersonDTO personDTO);

  PersonDTO toDTO(Person person);

}
