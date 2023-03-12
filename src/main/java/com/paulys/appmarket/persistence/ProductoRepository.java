package com.paulys.appmarket.persistence;

import com.paulys.appmarket.domain.Product;
import com.paulys.appmarket.domain.repository.ProductRepository;
import com.paulys.appmarket.persistence.crud.ProductoCrudRepository;
import com.paulys.appmarket.persistence.entity.Producto;
import com.paulys.appmarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;
    // Para consultar la lista de productos
    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        // Casteable
        return mapper.toProducts(productos);
    }
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }
    // Se mapea productos porque no hay mapeador y se lanza lambda (ver return)
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }
    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }
    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }
    // Eliminar un producto
    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
