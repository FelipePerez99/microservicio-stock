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
import org.springframework.data.domain.*;


import java.util.Arrays;

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

    @Test
    public void testListCategories() {
        // Arrange
        Category category1 = new Category(1, "Category A", "Description A");
        Category category2 = new Category(2, "Category B", "Description B");
        Pageable pageable = PageRequest.of(0, 2, Sort.by("name").ascending());
        Page<Category> page = new PageImpl<>(Arrays.asList(category1, category2), pageable, 2);

        when(objManageCategoryGateway.findAll(pageable)).thenReturn(page);

        // Act
        Page<Category> result = manageCategoryCUAdapter.listCategories(0, 2, "name", true);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getNumberOfElements());
        assertEquals("Category A", result.getContent().get(0).getName());
        assertEquals("Category B", result.getContent().get(1).getName());

        verify(objManageCategoryGateway, times(1)).findAll(pageable);
    }

    @Test
    public void testListCategoriesDescending() {
        // Arrange
        Category category1 = new Category(1, "Category B", "Description B");
        Category category2 = new Category(2, "Category A", "Description A");
        Pageable pageable = PageRequest.of(0, 2, Sort.by("name").descending());
        Page<Category> page = new PageImpl<>(Arrays.asList(category1, category2), pageable, 2);

        when(objManageCategoryGateway.findAll(pageable)).thenReturn(page);

        // Act
        Page<Category> result = manageCategoryCUAdapter.listCategories(0, 2, "name", false);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getNumberOfElements());
        assertEquals("Category B", result.getContent().get(0).getName());
        assertEquals("Category A", result.getContent().get(1).getName());

        verify(objManageCategoryGateway, times(1)).findAll(pageable);
    }

    @Test
    public void testListCategoriesAscending() {
        // Arrange
        Category category1 = new Category(1, "Category A", "Description A");
        Category category2 = new Category(2, "Category B", "Description B");
        Page<Category> expectedPage = new PageImpl<>(Arrays.asList(category1, category2));

        Pageable pageable = PageRequest.of(0, 2, Sort.by("name").ascending());
        when(objManageCategoryGateway.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Category> resultPage = manageCategoryCUAdapter.listCategories(0, 2, "name", true);

        // Assert
        assertNotNull(resultPage);
        assertEquals(2, resultPage.getTotalElements());
        assertEquals("Category A", resultPage.getContent().get(0).getName());
        assertEquals("Category B", resultPage.getContent().get(1).getName());
        verify(objManageCategoryGateway, times(1)).findAll(pageable);
    }
}
