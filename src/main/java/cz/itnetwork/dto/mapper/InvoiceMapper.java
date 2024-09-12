package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceEntity toEntity(InvoiceDTO dto);

    InvoiceDTO toDTO(InvoiceEntity entity);

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void editEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);

}