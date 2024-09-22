package cz.itnetwork.entity.repository;


import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;


/**
 * Třída definuje rozhraní pro přístup k datům faktur
 */
public interface InvoiceRepository extends PagingAndSortingRepository<InvoiceEntity, Long>,JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    @Query(value = "SELECT SUM(CASE WHEN `issued` > '2024-01-01' THEN `price` ELSE 0 END) AS `currentYearSum`, SUM(`price`) AS `allTimeSum`, COUNT(*) AS `invoicesCount` FROM `invoice`", nativeQuery = true)
     List<Object[]> getInvoiceStatistics();
}
