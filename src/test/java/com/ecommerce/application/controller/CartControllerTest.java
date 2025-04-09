package com.ecommerce.application.controller;

import com.ecommerce.application.dto.CartDTO;
import com.ecommerce.application.model.Cart;
import com.ecommerce.application.model.Product;
import com.ecommerce.application.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

public class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testCreateCart() throws Exception {
        // Mock cartService.createCart
        String cartId = "cart123";
        when(cartService.createCart()).thenReturn(cartId);

        // Test the POST /api/cart endpoint
        mockMvc.perform(post("/api/cart"))
                .andExpect(status().isOk())
                .andExpect(content().string(cartId));

        verify(cartService, times(1)).createCart();
    }

    @Test
    public void testAddProductToCart() throws Exception {
        String cartId = "cart123";
        Product product = new Product(1, "Test Product", 10.0);

        // Test the POST /api/cart/{cartId}/product endpoint
        mockMvc.perform(post("/api/cart/{cartId}/product", cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"description\": \"Test Product\", \"amount\": 10.0}"))
                .andExpect(status().isOk());

        // Capture the argument passed to addProductToCart
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(cartService, times(1)).addProductToCart(eq(cartId), productCaptor.capture());

        // Verify the captured product's properties
        Product capturedProduct = productCaptor.getValue();
        assertEquals(product.getId(), capturedProduct.getId());
        assertEquals(product.getDescription(), capturedProduct.getDescription());
        assertEquals(product.getAmount(), capturedProduct.getAmount(), 0.01);
    }

   @Test
public void testGetCartInfo() throws Exception {
    String cartId = "cart123";
    CartDTO cartDTO = new CartDTO(cartId, new ArrayList<>()); // Create a new CartDTO object
    when(cartService.getCartInfo(cartId)).thenReturn(cartDTO);

    mockMvc.perform(get("/api/cart/{cartId}", cartId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cartId").value(cartId));

    verify(cartService, times(1)).getCartInfo(cartId);
}

    @Test
    public void testDeleteCart() throws Exception {
        String cartId = "cart123";

        mockMvc.perform(delete("/api/cart/{cartId}", cartId))
                .andExpect(status().isNoContent());

        verify(cartService, times(1)).deleteCart(cartId);
    }
}
