package com.bootcamp.microservicio_stock.Domain.useCases;

import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.application.output.ManageBrandGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Brand;
import com.bootcamp.microservicio_stock.domain.useCases.ManageBrandCUAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManageBrandCUAdapterTest {
    @Mock
    private ManageBrandGatewayIntPort objManageBrandGateway;

    @Mock
    private FormatterResultsIntPort objFormatterResultsIntPort;

    @InjectMocks
    private ManageBrandCUAdapter manageBrandCUAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBrandSuccess() {
        // Arrange
        Brand brand = new Brand(0, "Unique Brand", "Some description");
        when(objManageBrandGateway.existsBrandyByName("Unique Brand")).thenReturn(false);
        when(objManageBrandGateway.save(any(Brand.class))).thenReturn(brand);

        // Act
        Brand createdBrand = manageBrandCUAdapter.createBrand(brand);

        // Assert
        assertNotNull(createdBrand);
        assertEquals("Unique Brand", createdBrand.getName());
        verify(objManageBrandGateway, times(1)).existsBrandyByName("Unique Brand");
        verify(objManageBrandGateway, times(1)).save(brand);
        verify(objFormatterResultsIntPort, never()).returnResponseErrorEntityExists(anyString());
    }

    @Test
    public void testCreateBrandDuplicate() {
        // Arrange
        Brand brand = new Brand(0, "Existing Brand", "Some description");
        when(objManageBrandGateway.existsBrandyByName("Existing Brand")).thenReturn(true);

        // Act
        Brand createdBrand = manageBrandCUAdapter.createBrand(brand);

        // Assert
        assertNull(createdBrand);
        verify(objManageBrandGateway, times(1)).existsBrandyByName("Existing Brand");
        verify(objManageBrandGateway, never()).save(any(Brand.class));
        verify(objFormatterResultsIntPort, times(1)).returnResponseErrorEntityExists("Error, en el sistema se encuentra una marca con ese nombre");
    }

    @Test
    public void testReturnResponseErrorEntityExistsCalled() {
        // Arrange
        Brand brand = new Brand(0, "Existing Brand", "Some description");
        when(objManageBrandGateway.existsBrandyByName("Existing Brand")).thenReturn(true);

        // Act
        manageBrandCUAdapter.createBrand(brand);

        // Assert
        verify(objFormatterResultsIntPort, times(1)).returnResponseErrorEntityExists("Error, en el sistema se encuentra una marca con ese nombre");
    }

    @Test
    public void testListBrandsSuccess() {
        // Arrange
        Brand brand1 = new Brand(1, "Brand A", "Description A");
        Brand brand2 = new Brand(2, "Brand B", "Description B");
        List<Brand> brands = Arrays.asList(brand1, brand2);
        Page<Brand> brandPage = new PageImpl<>(brands);
        when(objManageBrandGateway.findAllBrands(any(PageRequest.class))).thenReturn(brandPage);

        // Act
        Page<Brand> result = manageBrandCUAdapter.listBrands(0, 10, "name", true);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Brand A", result.getContent().get(0).getName());
        verify(objManageBrandGateway, times(1)).findAllBrands(any(PageRequest.class));
    }

    @Test
    public void testListBrandsDescendingOrder() {
        // Arrange
        Brand brand1 = new Brand(1, "Brand A", "Description A");
        Brand brand2 = new Brand(2, "Brand B", "Description B");
        List<Brand> brands = Arrays.asList(brand2, brand1); // Ordenado en forma descendente
        Page<Brand> brandPage = new PageImpl<>(brands);
        when(objManageBrandGateway.findAllBrands(any(PageRequest.class))).thenReturn(brandPage);

        // Act
        Page<Brand> result = manageBrandCUAdapter.listBrands(0, 10, "name", false);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Brand B", result.getContent().get(0).getName());
        verify(objManageBrandGateway, times(1)).findAllBrands(any(PageRequest.class));
    }

    @Test
    public void testListBrandsAscendingOrder() {
        // Arrange
        Brand brand1 = new Brand(1, "Brand A", "Description A");
        Brand brand2 = new Brand(2, "Brand B", "Description B");
        List<Brand> brands = Arrays.asList(brand1, brand2); // Ordenado en forma ascendente
        Page<Brand> brandPage = new PageImpl<>(brands);
        when(objManageBrandGateway.findAllBrands(any(PageRequest.class))).thenReturn(brandPage);

        // Act
        Page<Brand> result = manageBrandCUAdapter.listBrands(0, 10, "name", true);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Brand A", result.getContent().get(0).getName());
        verify(objManageBrandGateway, times(1)).findAllBrands(any(PageRequest.class));
    }
}
