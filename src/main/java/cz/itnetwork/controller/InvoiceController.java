package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO>getInvoices() {
        return invoiceService.getAll();
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoiceById(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/invoices/{invoiceId}")
    public InvoiceDTO deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceService.removeInvoice(invoiceId);
    }

   @PutMapping({"/invoices/{invoiceId}"})
    public InvoiceDTO editInvoice( @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long invoiceId) {
        return invoiceService.editInvoice(invoiceDTO, invoiceId);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        return invoiceService.getInvoiceStatistics();
    }


}



