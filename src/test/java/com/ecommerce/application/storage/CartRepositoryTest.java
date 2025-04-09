package com.ecommerce.application.storage;

import com.ecommerce.application.model.Cart;
import com.ecommerce.application.repository.CartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class CartRepositoryTest {

    private CartRepository cartRepository;

    @BeforeEach
    public void setUp() {
        cartRepository = new CartRepository(); // Creamos una nueva instancia antes de cada test
    }

    @Test
    public void testSaveCart() {
        // Crear un carrito de prueba
        Cart cart = new Cart("cart123", System.currentTimeMillis());
        
        // Guardamos el carrito
        cartRepository.saveCart(cart);
        
        // Verificamos que el carrito se guardó correctamente
        Cart retrievedCart = cartRepository.getCart("cart123");
        assertNotNull(retrievedCart, "The cart should be saved and retrievable.");
        assertEquals("cart123", retrievedCart.getCartId(), "The cart ID should match.");
    }

    @Test
    public void testGetCart() {
        // Crear un carrito de prueba
        Cart cart = new Cart("cart123", System.currentTimeMillis());
        cartRepository.saveCart(cart);

        // Verificar si el carrito puede ser recuperado
        Cart retrievedCart = cartRepository.getCart("cart123");
        assertNotNull(retrievedCart, "The cart should be retrievable.");
        assertEquals("cart123", retrievedCart.getCartId(), "The cart ID should match.");
    }

    @Test
    public void testDeleteCart() {
        // Crear y guardar un carrito
        Cart cart = new Cart("cart123", System.currentTimeMillis());
        cartRepository.saveCart(cart);

        // Eliminar el carrito
        cartRepository.deleteCart("cart123");

        // Verificar que el carrito ha sido eliminado
        Cart retrievedCart = cartRepository.getCart("cart123");
        assertNull(retrievedCart, "The cart should be null after deletion.");
    }

    @Test
    public void testGetAllCarts() {
        // Crear carritos de prueba
        Cart cart1 = new Cart("cart123", System.currentTimeMillis());
        Cart cart2 = new Cart("cart456", System.currentTimeMillis());
        
        // Guardamos los carritos
        cartRepository.saveCart(cart1);
        cartRepository.saveCart(cart2);
        
        // Obtener todos los carritos
        Collection<Cart> allCarts = cartRepository.getAllCarts();
        
        // Verificar que los carritos fueron guardados
        assertEquals(2, allCarts.size(), "There should be 2 carts stored.");
    }

    @Test
    public void testPrintAllCarts() {
        // Crear un carrito
        Cart cart = new Cart("cart123", System.currentTimeMillis());
        
        // Guardar el carrito
        cartRepository.saveCart(cart);
        
        // Imprimir todos los carritos (esto se ejecuta solo con el método printAllCarts)
        cartRepository.printAllCarts();
        
        // Aunque no podemos verificar la salida en consola, podemos verificar que no haya excepciones
        assertDoesNotThrow(() -> cartRepository.printAllCarts(), "Printing all carts should not throw exceptions.");
    }
}
