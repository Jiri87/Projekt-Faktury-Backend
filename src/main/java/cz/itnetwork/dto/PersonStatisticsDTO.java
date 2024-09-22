package cz.itnetwork.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatisticsDTO {

    private Long personId;  // ID osoby, které umožňuje jednoznačně identifikovat osobu v systému.

    private String personName;  // Jméno osoby.

    private BigDecimal revenue;  //  Celkový příjem osoby.

}
