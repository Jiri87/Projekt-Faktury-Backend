package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;

import java.util.List;

public interface PersonService {


    PersonDTO addPerson(PersonDTO personDTO);

    void removePerson(long id);

    List<PersonDTO> getAll();

    PersonDTO getPersonById(long id);

    PersonDTO editPerson(long personId, PersonDTO personDTO);

    List<InvoiceDTO> getPurchases (String ico);

    List<PersonStatisticsDTO> getPersonStatistics();

}
