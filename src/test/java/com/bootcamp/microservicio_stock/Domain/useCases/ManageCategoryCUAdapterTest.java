package com.bootcamp.microservicio_stock.Domain.useCases;

import com.bootcamp.microservicio_stock.application.output.FormatterResultsIntPort;
import com.bootcamp.microservicio_stock.application.output.ManageCategoryGatewayIntPort;
import com.bootcamp.microservicio_stock.domain.models.Category;
import com.bootcamp.microservicio_stock.domain.useCases.ManageCategoryCUAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManageCategoryCUAdapterTest {

    @Mock
    private ManageCategoryGatewayIntPort objManageCategoryGateway;

    @Mock
    private FormatterResultsIntPort objFormatterResultsIntPort;

    @InjectMocks
    private ManageCategoryCUAdapter manageCategoryCUAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategorySuccess() {
        // Arrange
        Category category = new Category(0, "Unique Category", "Some description");
        when(objManageCategoryGateway.existsCategoryByName("Unique Category")).thenReturn(false);
        when(objManageCategoryGateway.save(any(Category.class))).thenReturn(category);

        // Act
        Category createdCategory = manageCategoryCUAdapter.create(category);

        // Assert
        assertNotNull(createdCategory);
        assertEquals("Unique Category", createdCategory.getName());
        verify(objManageCategoryGateway, times(1)).existsCategoryByName("Unique Category");
        verify(objManageCategoryGateway, times(1)).save(category);
        verify(objFormatterResultsIntPort, never()).returnResponseErrorEntityExists(anyString());
    }

    @Test
    public void testCreateCategoryDuplicate() {
        // Arrange
        Category category = new Category(0, "Existing Category", "Existing description");
        when(objManageCategoryGateway.existsCategoryByName("Existing Category")).thenReturn(true);

        // Act
        Category createdCategory = manageCategoryCUAdapter.create(category);

        // Assert
        assertNull(createdCategory);
        verify(objManageCategoryGateway, times(1)).existsCategoryByName("Existing Category");
        verify(objManageCategoryGateway, never()).save(any(Category.class));
        verify(objFormatterResultsIntPort, times(1)).returnResponseErrorEntityExists("Error, en el sistema se encuentra una categoria con ese nombre");
    }

    @Test
    public void testReturnResponseErrorEntityExistsCalled() {
        // Arrange
        Category category = new Category(0, "Existing Category", "Existing description");
        when(objManageCategoryGateway.existsCategoryByName("Existing Category")).thenReturn(true);

        // Act
        manageCategoryCUAdapter.create(category);

        // Assert
        verify(objFormatterResultsIntPort, times(1)).returnResponseErrorEntityExists("Error, en el sistema se encuentra una categoria con ese nombre");
    }

    @Test
    public void testSaveCategoryWhenNoError() {
        // Arrange
        Category category = new Category(0, "Another Unique Category", "Another description");
        when(objManageCategoryGateway.existsCategoryByName("Another Unique Category")).thenReturn(false);
        when(objManageCategoryGateway.save(any(Category.class))).thenReturn(category);

        // Act
        Category createdCategory = manageCategoryCUAdapter.create(category);

        // Assert
        assertNotNull(createdCategory);
        verify(objManageCategoryGateway, times(1)).save(category);
        verify(objFormatterResultsIntPort, never()).returnResponseErrorEntityExists(anyString());
    }


}
