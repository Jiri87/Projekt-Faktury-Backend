package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    @Query(value = "SELECT SUM(CASE WHEN `issued` > '2024-01-01' THEN `price` ELSE 0 END) AS `currentYearSum`, SUM(`price`) AS `allTimeSum`, COUNT(*) AS `invoicesCount` FROM `invoice`", nativeQuery = true)
     List<Object[]> getInvoiceStatistics();
}
