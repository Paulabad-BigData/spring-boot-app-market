package com.paulys.appmarket.persistence.mapper;

import com.paulys.appmarket.domain.Category;
import com.paulys.appmarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(target = "category", source = "descripcion"),
            @Mapping(target = "categoryId", source = "idCategoria"),
            @Mapping(target = "active", source = "estado"),
    })
    Category toCategory(Categoria categoria);

    //Conversión externa @Mapping ignore para que producto esta dentro de Categoria sea ignorado
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
