package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import java.util.List;


/**
 * Toto rozhraní definuje metody pro správu osob a souvisejících faktur
 */
public interface PersonService {


    PersonDTO newPerson(PersonDTO personDTO);  // Přidá novou osobu a vrátí její DTO

    void removePerson(long id);  // Odstraní osobu podle jejího ID

    List<PersonDTO> getAll();  // Vrátí seznam všech osob

    PersonDTO getPersonById(long id);   // Vrátí osobu podle jejího ID

    PersonDTO editPerson(long personId, PersonDTO personDTO);   // Upraví osobu podle jejího ID a vrátí aktualizované DTO

    List<InvoiceDTO> getPurchases (String ico);   // Vrátí seznam nákupů pro danou osobu podle IČO

    List<PersonStatisticsDTO> getPersonStatistics();  // Vrátí statistiky pro osoby
}
