package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAll();

    InvoiceDTO getInvoiceById(long id);

    void removeInvoice (long id);

}
