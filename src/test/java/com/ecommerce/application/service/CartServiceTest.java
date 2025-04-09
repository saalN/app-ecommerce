package com.ecommerce.application.service;

import com.ecommerce.application.dto.CartDTO;
import com.ecommerce.application.model.Cart;
import com.ecommerce.application.model.Product;
import com.ecommerce.application.repository.CartRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
private CartRepository cartRepository;

@InjectMocks
private CartService cartService;

@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
public void testCreateCartGeneratesIdAndStoresCart() {
    String cartId = cartService.createCart();
    assertNotNull(cartId);
    verify(cartRepository, times(1)).saveCart(any(Cart.class));
}

@Test
public void testAddProductToCart() {
    String cartId = UUID.randomUUID().toString();
    Product product = new Product(1, "Test Product", 10.0);
    Cart cart = new Cart(cartId, System.currentTimeMillis());

    when(cartRepository.getCart(cartId)).thenReturn(cart);

    cartService.addProductToCart(cartId, product);

    assertEquals(1, cart.getProducts().size());
    assertEquals("Test Product", cart.getProducts().get(0).getDescription());
}

@Test
public void testGetCartInfoReturnsCorrectCart() {
    String cartId = "abc123";
    Cart cart = new Cart(cartId, System.currentTimeMillis());

    when(cartRepository.getCart(cartId)).thenReturn(cart);

    CartDTO result = cartService.getCartInfo(cartId);

    assertEquals(cartId, result.getCartId());
}

@Test
public void testDeleteCartCallsStorage() {
    String cartId = "abc123";

    cartService.deleteCart(cartId);

    verify(cartRepository, times(1)).deleteCart(cartId);
}

   
}
