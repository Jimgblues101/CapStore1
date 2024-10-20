package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerTest {


    //
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/shopping_store/category";
    private static Category category;
    private static Category category2;
    private static Category category3; // Store this to reuse in tests

    @BeforeAll
    public static void setup() {
        category = CategoryFactory.buildCategory(
                null,
                "Men"
                );
        category2 = CategoryFactory.buildCategory(
                null,
                "Women");
        category3 = CategoryFactory.buildCategory(
                null,
                "Kids"
        );
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Category> postResponse2 = restTemplate.postForEntity(url, category, Category.class);
        assertNotNull(postResponse2);
        assertNotNull(postResponse2.getBody());
        category = postResponse2.getBody();  // Capture and reuse the created category
        assertNotNull(category.getId());  // Ensure ID is not null after creation
        System.out.println("Create: " + category);

        ResponseEntity<Category> postResponse = restTemplate.postForEntity(url, category2, Category.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        category2 = postResponse.getBody();  // Capture and reuse the created category
        assertNotNull(category2.getId());  // Ensure ID is not null after creation
        System.out.println("Create2: " + category2);

        ResponseEntity<Category> postResponse3 = restTemplate.postForEntity(url, category3, Category.class);
        assertNotNull(postResponse3);
        assertNotNull(postResponse3.getBody());
        category3 = postResponse3.getBody();  // Capture and reuse the created category
        assertNotNull(category3.getId());  // Ensure ID is not null after creation
        System.out.println("Create3: " + category3);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + category.getId();
        System.out.println("\nURL: " + url);
        ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);
        assertEquals(category.getId(), response.getBody().getId());
        System.out.println("Read: " + response.getBody());

        String url2 = BASE_URL + "/read/" + category2.getId();
        System.out.println("\nURL: " + url2);
        ResponseEntity<Category> response2 = restTemplate.getForEntity(url2, Category.class);
        assertEquals(category2.getId(), response2.getBody().getId());
        System.out.println("Read2: " + response2.getBody());

        String url3 = BASE_URL + "/read/" + category3.getId();
        System.out.println("\nURL: " + url3);
        ResponseEntity<Category> response3 = restTemplate.getForEntity(url3, Category.class);
        assertEquals(category3.getId(), response3.getBody().getId());
        System.out.println("Read3: " + response3.getBody());
    }

    @Test
    @Order(3)
    void update() {
        Category updated = new Category.Builder().copy(category).setName("Kids").build();

        String url = BASE_URL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Update category: " + updated);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Category> entity = new HttpEntity<>(updated, headers);
        ResponseEntity<Category> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Category.class);

        assertNotNull(response.getBody());
        System.out.println("Updated category: " + response.getBody().getName());
    }

    @Test
    @Order(4)
    void delete() {
        String url = BASE_URL + "/delete/" + category.getId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Category deleted: " + category.getId());
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All categories: ");
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

//    @Test
//    @Order(6)
//    void findByNameWithinIgnoreCase() {
//        String url = BASE_URL + "/getByCategoryName/" + category.getCategoryName();
//        System.out.println("URL: " + url);
//        ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);
//        assertEquals(category.getCategoryName(), response.getBody().getCategoryName());
//    }
//
//    @Test
//    @Order(7)
//    void findBySubCategories_Id() {
//        String url = BASE_URL + "/getByCategoryId/" + category.getCategoryId();
//        System.out.println("URL: " + url);
//        ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);
//        assertEquals(category.getCategoryId(), response.getBody().getCategoryId());
//    }
}