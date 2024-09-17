package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAll();

    InvoiceDTO getInvoiceById(long id);

    InvoiceDTO removeInvoice (long id);

    InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id);

    InvoiceStatisticsDTO getInvoiceStatistics();



}
