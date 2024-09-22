package cz.itnetwork.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


/**
 * Tabulka faktury
 */
@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primární klíč


    @Column(nullable = false)
    private int invoiceNumber;  // Číslo faktury

    @Column(nullable = false)
    private LocalDate issued;  // Datum vystavení faktury

    @Column(nullable = false)
    private LocalDate dueDate;  // Datum splatnosti faktury

    @Column(nullable = false)
    private String product;  // Produkt na faktuře

    @Column(nullable = false)
    private int price;  // Cena za fakturu

    @Column(nullable = false)
    private int vat;  // DPH

    @Column(nullable = false)
    private String note;  // Poznámka

    @ManyToOne
    private PersonEntity seller;  // Dodavatel

    @ManyToOne
    private PersonEntity buyer;  // Odběratel

}
