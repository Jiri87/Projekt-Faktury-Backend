package cz.itnetwork.entity.filter;


import lombok.Data;


/**
 *Definice filtračních kritérií pro získání faktur
 */

@Data
public class InvoiceFilter {

   private Long sellerId;  // ID dodavatele. Umožňuje filtrovat faktury podle konkrétního dodavatele.
   private Long buyerId;  // ID odběratele. Umožňuje filtrovat faktury podle konkrétního odběratele.
   private String product;  // Název produktu. Umožňuje filtrovat faktury podle specifického produktu, který je uveden na faktuře.
   private Long minPrice;  //  Minimální cena. Používá se k filtrování faktur, které mají cenu vyšší nebo rovnou této hodnotě.
   private Long maxPrice;  // Maximální cena. Používá se k filtrování faktur, které mají cenu nižší nebo rovnou této hodnotě.
   private int limit=20;  // Počet faktur, které se mají vrátit. Výchozí hodnota je nastavena na 20, což znamená, že pokud není limit specifikován, vrátí se maximálně 20 faktur.

}
