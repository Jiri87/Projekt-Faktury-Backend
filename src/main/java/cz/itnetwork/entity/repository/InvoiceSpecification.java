package cz.itnetwork.entity.repository;


import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {
    private final InvoiceFilter invoiceFilter;

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (invoiceFilter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), invoiceFilter.getMinPrice()));
        }

        if (invoiceFilter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), invoiceFilter.getMaxPrice()));
        }

        // Přidání dalších filtrů na základě product, buyerID a sellerID
        if (invoiceFilter.getProduct() != null && !invoiceFilter.getProduct().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("product"), invoiceFilter.getProduct()));
        }

        if (invoiceFilter.getBuyerId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("buyer").get("id"), invoiceFilter.getBuyerId()));
        }

        if (invoiceFilter.getSellerId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("seller").get("id"), invoiceFilter.getSellerId()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}