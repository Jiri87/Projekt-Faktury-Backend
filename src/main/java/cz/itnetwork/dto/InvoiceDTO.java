package cz.itnetwork.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceDTO {

    @JsonProperty("_id")
    private Long id;  // Identifikátor faktury

    private int invoiceNumber;  // Číslo faktury

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;  // Datum vystavení faktury

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;  // Datum splatnosti faktury

    private String product;  // Název produktu na faktuře

    private int price; // Cena produktu na faktuře

    private int vat;  // Sazba DPH na faktuře

    private String note;  // Poznámka na faktuře

    private PersonDTO seller;  // Prodejce faktury

    private PersonDTO buyer;  // Kupující na faktuře

}