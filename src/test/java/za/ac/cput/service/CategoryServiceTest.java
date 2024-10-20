/**
 * CategoryServiceTest.java
 * Test for the CategoryService
 * Author: Mthandeni Mbobo (218223579)
 */

package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    private static Category category;

    @Test
    @Order(1)
    void setup() {
        category = CategoryFactory.buildCategory(
                null,
                "Shoes"
        );

        assertNotNull(category);
        System.out.println("Category: " + category);
    }

    @Test
    @Order(2)
    void create() {
        Category created = categoryService.create(category);
        assertEquals(category.getId(), created.getId());
        System.out.println("Created category: " + created);
    }

    @Test
    @Order(3)
    void read() {
        Category read = categoryService.read(category.getId());
        assertNotNull(read);
        System.out.println("Read category: " + read);
    }

    @Test
    @Order(4)
    void update() {
        Category newCategory = new Category.Builder().copy(category).setName("Women").setName("Tops").build();
        Category updated = categoryService.update(newCategory);
        assertEquals(newCategory.getName(), updated.getName());
        System.out.println("Updated category: " + updated);
    }

    @Test
    @Order(5)
    @Disabled
    void delete() {
        boolean deleted = categoryService.delete(category.getId());
        assertTrue(deleted);
        System.out.println("Category deleted: " + deleted);
    }

    @Test
    @Order(6)
    void findAll() {
        System.out.println("Show all categories:\n" + categoryService.findAll());
    }

    @Test
    @Order(7)
    void findByCategoryId() {
        System.out.println("Category by Id: " + categoryService.read(category.getId()));
    }



}