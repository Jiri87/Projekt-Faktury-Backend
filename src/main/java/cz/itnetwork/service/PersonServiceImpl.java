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
package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;


    /**
     * Přidá novou osobu do systému.
     * @param personDTO DTO objekt osoby, který obsahuje potřebné informace.
     * @return DTO objekt nově přidané osoby.
     */
    public PersonDTO newPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);

        return personMapper.toDTO(entity);
    }

    /**
     * Odstraní a skryje osobu v systému podle jejího ID.
     * @param personId  ID osoby, která má být skryta.
     */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.save(person);
        } catch (NotFoundException ignored) {
            // Pokud není osoba nalezena, neprovádí se žádná akce (podle kontraktu v rozhraní)
        }
    }

    /**
     * Vrátí seznam všech osob
     * @return Seznam ne-skrytých osob jako PersonDTO.
     */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * Získá osobu podle ID.
     * @param id ID osoby
     * @return DTO osoby
     */
    @Override
    public PersonDTO getPersonById(long id) {
        return personMapper.toDTO(fetchPersonById(id));
    }

    /**
     * Uprava osoby na základě ID.
     * @param personId ID osoby k úpravě.
     * @param personDTO Nové údaje osoby.
     * @return DTO upravené osoby.
     */
    @Override
    public  PersonDTO editPerson(long personId, PersonDTO personDTO) {
        PersonEntity originalPerson = fetchPersonById(personId);
        originalPerson.setHidden(true);
        personRepository.save(originalPerson); // uloží původní entitu, která bude skrytá
        PersonEntity newPerson = personMapper.toEntity(personDTO);
        newPerson = personRepository.save(newPerson);
        return personMapper.toDTO(newPerson);
    }

    /**
     * Tato metoda slouží k načtení entity osoby na základě jejího ID. Pokud osoba s daným ID neexistuje
     * , vyhazuje výjimku NotFoundException.
     * @param id ID osoby, kterou chcete načíst.
     * @return Načtená entita osoby.
     */
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }

    /**
     * Tato metoda slouží k načtení všech faktur (nákupů) spojených s
     * konkrétní osobou identifikovanou jejím IČO (identifikačním číslem).
     * @param ico  Identifikační číslo osoby, jejíž nákupy chcete načíst.
     * @return Seznam faktur (InvoiceDTO) spojených s daným IČO.
     */
    public List<InvoiceDTO> getPurchases(String ico) {
        return  personRepository.findAll()
                .stream()
                .map(PersonEntity::getPurchases)
                .flatMap(List::stream)
                .filter(i -> i.getBuyer().getIdentificationNumber().equals(ico))
                .map(i -> invoiceMapper.toDTO(i))
                .toList();
    }


    /**
     * Tato metoda slouží k načtení statistik o osobách, včetně jejich příjmů
     * , na základě dat získaných z repozitáře.
     * @return Seznam statistik osob (List<PersonStatisticsDTO>).
     */
    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
           List<Object[]> results = personRepository.getPersonStatistics();

            // Převod výsledků na seznam PersonStatisticsDTO
            return results.stream()
                    .map(record -> new PersonStatisticsDTO(
                            (Long) record[0],           // ID osoby
                            (String) record[1],         // Jméno osoby
                            (BigDecimal) record[2]      // Příjmy
                    ))
                    .collect(Collectors.toList());
        }
    }



