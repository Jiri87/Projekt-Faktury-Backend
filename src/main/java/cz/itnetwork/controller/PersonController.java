/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.controller;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Přidání nové osoby
     * @param personDTO DTO objekt s informacemi o osobě, které budou přidány.
     * @return Nově přidaná osoba jako objekt PersonDTO.
     */
    @PostMapping("/persons")
    public PersonDTO newPerson(@RequestBody PersonDTO personDTO) {
        return personService.newPerson(personDTO);
    }

    /**
     * Získání seznamu všech osob
     * @return Seznam všech osob jako List<PersonDTO>.
     */
    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {
        return personService.getAll();
    }

    /**
     * Odstraní osobu na základě jejího ID.
     * @param personId ID osoby, která má být odstraněna.
     */
    @DeleteMapping("/persons/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    /**
     * Získá osobu na základě jejího ID.
     * @param personId ID osoby, která má být nalezena.
     * @return Data nalezené osoby ve formátu PersonDTO.
     */
    @GetMapping("/persons/{personId}")
    public PersonDTO getPersonById(@PathVariable Long personId){
       return personService.getPersonById(personId);
    }

    /**
     * Upraví o osobě na základě jejího ID
     * @param personId personId ID osoby, jejíž údaje mají být upraveny.
     * @param personDTO personDTO Nové údaje osoby ve formátu PersonDTO.
     * @return Aktualizované údaje osoby ve formátu PersonDTO.
     */
    @PutMapping({"/persons/{personId}"})
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
        return personService.editPerson(personId, personDTO);
    }

    /**
     * Získá seznam faktur spojeným s daným identifikačním číslem
     * @param identificationNumber Identifikační číslo osoby
     * @return Seznam faktur ve formátu InvoiceDTO.
     */
    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchases(@PathVariable String identificationNumber) {
        return personService.getPurchases(identificationNumber);
    }

    /**
     * Získá statistiky osob ve formátu PersonStatisticsDTO.
     * @return Seznam statistik osob.
     */
    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonStatistics() {
       return personService.getPersonStatistics();
    }


}

