package cz.itnetwork.controller;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
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

    /**
     * Vytvoření nové faktury
     * Přijímá JSON data ve formátu InvoiceDTO a vytváří novou fakturu.
     * @param invoiceDTO Data nové faktury poslaná klientem
     * @return Vytvořená faktura ve formátu InvoiceDTO
     */
    @PostMapping("/invoices")
    public InvoiceDTO newInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.newInvoice(invoiceDTO);
    }

    /**
     * Získání seznamu faktur na základě zadaných filtrů.
     * @param invoiceFilter Parametry pro filtrování faktur
     * @return Seznam faktur ve formátu InvoiceDTO odpovídající zadaným filtrům
     */
    @GetMapping("/invoices")
    public List<InvoiceDTO>getInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getAll(invoiceFilter);
    }

    /**
     * Získání konkrétní faktury dle jejího ID
     * @param invoiceId ID faktury, které chceme získat
     * @return Faktura ve formátu InvoiceDTO odpovídající zadanému ID
     */
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoiceById(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    /**
     * Odstranění faktury na základě jejího ID
     * @param invoiceId ID faktury, kterou chceme odstranit
     * @return Vrací smazanou fakturu ve formatu InvoiceDTO
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/invoices/{invoiceId}")
    public InvoiceDTO deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceService.removeInvoice(invoiceId);
    }

    /**
     * Úprava existující faktury dle jejího ID
     * @param invoiceDTO Obsahuje aktualizovaná data faktury
     * @param invoiceId ID faktury, kterou chceme upravit
     * @return Vrací aktualizovanou fakturu ve formátu InvoiceDTO
     */
   @PutMapping({"/invoices/{invoiceId}"})
    public InvoiceDTO editInvoice( @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long invoiceId) {
        return invoiceService.editInvoice(invoiceDTO, invoiceId);
    }


    /**
     * Získání statistik faktur
     * @return Vrací statistické údaje o fakturách ve formátu InvoiceStatisticsDTO.
     */
    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        return invoiceService.getInvoiceStatistics();
    }


}



