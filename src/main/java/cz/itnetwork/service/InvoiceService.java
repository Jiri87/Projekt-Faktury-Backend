package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import java.util.List;

/**
 * Správa faktur
 */
public interface InvoiceService {

    InvoiceDTO newInvoice (InvoiceDTO invoiceDTO);  // Přidává novou fakturu do systému

    List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter);  // Vrací seznam všech faktur splňující kritéria filtru

    InvoiceDTO getInvoiceById(long id);  // Získává konkrétní fakturu pomocí ID

    InvoiceDTO removeInvoice (long id);  // Odstraňuje fakturu dle ID

    InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id);  // Upravuje fakturu dle ID

    InvoiceStatisticsDTO getInvoiceStatistics();  // Získává statistky o fakturách



}
