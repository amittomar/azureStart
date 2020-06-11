package com.tomar.bootsample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@RestController
public class PersonRestController {

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

  @ResponseBody()
  @RequestMapping(path ="/api/v1/getWithRequestParam", produces = {"application/JSON" } , method = RequestMethod.GET)
  public List<Object> getWithRequestParam(@RequestParam(value = "personDTO") String personDTO)
      throws IOException {
    final PersonDTO person = new ObjectMapper().setDateFormat(simpleDateFormat).readValue(personDTO, PersonDTO.class);
    
    return Arrays.asList(
        person.getFirstName(),
        person.getSecondName(),
        person.getDateOfBirth(),
        person.getProfession(),
        person.getSalary());
  }

  @RequestMapping(path= "/getWithoutRequestParam", method = RequestMethod.GET)
  public List<Object> getWithoutRequestParam(PersonDTO personDTO) {
    return Arrays.asList(
        personDTO.getFirstName(),
        personDTO.getSecondName(),
        personDTO.getDateOfBirth(),
        personDTO.getProfession(),
        personDTO.getSalary());
  }

  @RequestMapping(path = "/getWithMultipleParameters", method = RequestMethod.GET)
  public List<Object> getWithMultipleParameters(
      PersonDTO personDTO, @RequestParam(value = "firstName") String firstName) {
    return Arrays.asList(
        personDTO.getFirstName(),
        personDTO.getSecondName(),
        personDTO.getDateOfBirth(),
        personDTO.getProfession(),
        personDTO.getSalary(),
        firstName);
  }

  @RequestMapping(path ="/getWithMultipleRequestParams",method = RequestMethod.GET)
  public List<Object> getWithMultipleRequestParams(
      @RequestParam(value = "personDTO") String personDTO,
      @RequestParam(value = "firstName") String firstName)
      throws IOException {
    final PersonDTO person =
        new ObjectMapper().setDateFormat(simpleDateFormat).readValue(personDTO, PersonDTO.class);
    return Arrays.asList(
        person.getFirstName(),
        person.getSecondName(),
        person.getDateOfBirth(),
        person.getProfession(),
        person.getSalary(),
        firstName);
  }
}
