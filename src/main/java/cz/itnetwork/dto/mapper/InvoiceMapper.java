package cz.itnetwork.dto.mapper;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


/**
 *
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    /**
     * Převádí DTO na entitu.
     * @param dto objekt InvoiceDTO.
     * @return objekt InvoiceEntity.
     */
    InvoiceEntity toEntity(InvoiceDTO dto);

    /**
     * Převádí entitu na DTO.
     * @param entity objekt InvoiceEntity.
     * @return objekt InvoiceDTO.
     */
    InvoiceDTO toDTO(InvoiceEntity entity);

    /**
     * Aktualizuje existující entitu na základě DTO, ignoruje pole buyer a seller.
     * @param source zdrojový objekt InvoiceDTO.
     * @param target cílový objekt InvoiceEntity, který má být aktualizován.
     */
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void editEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);

}