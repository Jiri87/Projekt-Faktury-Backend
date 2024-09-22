package cz.itnetwork.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticsDTO {

    private BigDecimal currentYearSum;  // Celková částka faktur za aktuální rok

    private BigDecimal allTimeSum;  // Celková částka faktur od začátku sledování

    private BigDecimal invoicesCount;  // Počet všech faktur
}
