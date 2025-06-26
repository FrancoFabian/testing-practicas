package com.practicando.demo.steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import com.practicando.demo.entity.Product;
import com.practicando.demo.repository.ProductRepository;


import java.util.Optional;


public class ProductSteps {
    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @Given("el sistema esta disponible")
    public void el_sistema_esta_disponible() {
        assertNotNull(productRepository);
    }
    @When("creo un producto con el nombre {string}")
    public void creo_un_product_con_el_nombre(String nombre) {
        product = Product.builder()
                .name(nombre)
                .stock(0)      // valor inicial
                .price(0.0)    // si tu entidad tiene price not null
                .build();
        productRepository.save(product);
    }

    @Then("el producto {string} debe estar creado")
    public void el_producto_debe_estar_creado(String nombre) {
        Optional<Product> econtrado = productRepository.findByName(nombre);
        assertTrue(econtrado.isPresent());
        assertEquals(econtrado.get().getName(), nombre);
    }
}
