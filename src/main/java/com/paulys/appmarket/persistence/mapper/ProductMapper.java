package com.paulys.appmarket.persistence.mapper;

import com.paulys.appmarket.domain.Product;
import com.paulys.appmarket.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(target = "productId", source = "idProducto"),
            @Mapping(target = "name", source = "nombre"),
            @Mapping(target = "categoryId", source = "idCategoria"),
            @Mapping(target = "price", source = "precioVenta"),
            @Mapping(target = "stock", source = "cantidadStock"),
            @Mapping(target = "active", source = "estado"),
            @Mapping(target = "category", source = "categoria")
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
