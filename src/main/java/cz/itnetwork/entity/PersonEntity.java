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
package cz.itnetwork.entity;


import cz.itnetwork.constant.Countries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


/**
 * Tabulka osoby
 */
@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Primární klíč

    @Column(nullable = false)
    private String name;  // Jméno osoby

    @Column(nullable = false)
    private String identificationNumber;  // Identifikační číslo

    private String taxNumber;  // Daňové číslo

    @Column(nullable = false)
    private String accountNumber;  // Číslo účtu

    @Column(nullable = false)
    private String bankCode;  // Bankovní číslo

    private String iban;  // Číslo IBAN

    @Column(nullable = false)
    private String telephone;  // Telefon osoby

    @Column(nullable = false)
    private String mail;  // Email osoby

    @Column(nullable = false)
    private String street;  // Ulice

    @Column(nullable = false)
    private String zip;  // Číslo popisné

    @Column(nullable = false)
    private String city;  // Město

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Countries country;  // Země

    private String note;  // Poznámka

    private boolean hidden = false;


    @OneToMany(mappedBy = "buyer")
    private List<InvoiceEntity> purchases;  // Seznam faktur, které osoba zakoupila

    @OneToMany(mappedBy = "seller")
    private List<InvoiceEntity> sales;  // Seznam faktur, které osoby prodala


}
