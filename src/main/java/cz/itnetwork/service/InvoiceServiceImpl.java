package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.InvoiceSpecification;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;


    /**
     * @param invoiceDTO
     * @return
     */
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
        PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());
        entity.setBuyer(buyer);
        entity.setSeller(seller);
        entity = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(entity);
    }

// výpis faktur + filtrace


    @Override
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);


        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());


    }

    /**
     * Detail faktury
     *
     * @param id
     * @return
     */
    @Override
    public InvoiceDTO getInvoiceById(long id) {
        return invoiceMapper.toDTO(fetchInvoiceById(id));
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    //smazání faktury
    @Override
    public InvoiceDTO removeInvoice(long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice with id " + id + " wasn't found in the database."));
        InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoice);
        invoiceRepository.delete(invoice);
        return null;
    }

    //uprava faktury

    @Override
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id) {
        invoiceDTO.setId(id);
        InvoiceEntity entity = fetchInvoiceById(id);
        invoiceMapper.editEntity(invoiceDTO, entity);

        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        InvoiceEntity saved = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(saved);


    }

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        List<Object[]> result = invoiceRepository.getInvoiceStatistics();
        Object[] record = result.get(0);

        BigDecimal totalInvoices = BigDecimal.valueOf(((Number) record[0]).longValue()); // Pokud je to Long nebo Integer
        BigDecimal totalBuyers = BigDecimal.valueOf(((Number) record[1]).longValue());   // Pokud je to Long nebo Integer
        BigDecimal totalAmount = BigDecimal.valueOf(((Number) record[2]).longValue()); // Očekáváme, že celková částka je BigDecimal

        return new InvoiceStatisticsDTO(totalInvoices, totalBuyers, totalAmount);

    }
}






       //return new InvoiceStatisticsDTO((BigDecimal) record[0], (BigDecimal) record[1] , (BigDecimal) record[2]

