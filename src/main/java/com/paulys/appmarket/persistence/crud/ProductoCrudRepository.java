package com.paulys.appmarket.persistence.crud;

import com.paulys.appmarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // De manera nativa @Query
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    //Lista los productos por nombre de manera ascedente
    default List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria) {
        return null;
    }

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
