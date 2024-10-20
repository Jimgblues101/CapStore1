package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Orders;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.IOrderItemRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private  IOrderItemRepository orderItemRepository;

    @Autowired
    private  OrderService orderService; // Add OrderService to link OrderItem with Order

    private static Orders testOrder;

    private Address address;
    private  Product product;

    @BeforeEach
     void setUp() {
        address = new Address();
        product = new Product();
        // Setup a test order
        testOrder = new Orders.Builder()
                .setId(55L)
                .setAddress(address)
                .setStatus("Pending")
                .setTotalPrice(100.0)
                .setOrderDate(LocalDate.now())
                .build();
        testOrder = orderService.create(testOrder); // Save order to get an ID
    }

    @AfterEach
     void tearDown() {
        // Clean up data after each test
        orderItemRepository.deleteAll();
        orderService.deleteByOrderID(testOrder.getId()); // Clean up test order
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("Create");
        OrderItem orderItem = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(5)
                .setPrice(100.0)
                .setOrder(testOrder) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);

        System.out.println("created test Method");
        System.out.println(createdOrderItem);
        System.out.println("-----------------------------");
        assertNotNull(createdOrderItem);
        assertNotNull(createdOrderItem.getId());
        assertEquals(1L, createdOrderItem.getProduct());
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("find By Id/ read");
        OrderItem orderItem = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(10)
                .setPrice(200.0)
                .setOrder(testOrder) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println("read method");
        System.out.println(createdOrderItem);
        System.out.println("----------------");
        OrderItem readOrderItem = orderItemService.read(createdOrderItem.getId());
        System.out.println("reading the Order Item");
        System.out.println(readOrderItem);
        System.out.println("------------------------");
        assertNotNull(readOrderItem);
        assertEquals(createdOrderItem.getId(), readOrderItem.getId());
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("Update");
        OrderItem orderItem = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(15)
                .setPrice(300.0)
                .setOrder(testOrder) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .setId(createdOrderItem.getId())
                .setProduct(product) // unchanged
                .setQuantity(20)
                .setPrice(350.0)
                .setOrder(testOrder) // Link to the test order
                .build();
        System.out.println(updatedOrderItem);
        OrderItem result = orderItemService.update(updatedOrderItem);
        assertNotNull(result);
        assertEquals(20, result.getQuantity());
        assertEquals(350.0, result.getPrice());
    }

    @Test
    @Order(4)
    void findAll() {
        System.out.println("find All");
        OrderItem orderItem1 = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(10)
                .setPrice(400.0)
                .setOrder(testOrder) // Link to the test order
                .build();

        OrderItem orderItem2 = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(5)
                .setPrice(500.0)
                .setOrder(testOrder) // Link to the test order
                .build();

        orderItemService.create(orderItem1);
        orderItemService.create(orderItem2);

        List<OrderItem> orderItems = orderItemService.findAll();
        System.out.println(orderItems);
        assertNotNull(orderItems);
        assertEquals(2, orderItems.size());
    }

    @Test
    @Order(5)
    void findById() {
        OrderItem orderItem = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(8)
                .setPrice(700.0)
                .setOrder(testOrder) // Link to the test order
                .build();
        System.out.println("find By Id");
        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        OrderItem foundOrderItem = orderItemService.read(createdOrderItem.getId());
        System.out.println(foundOrderItem);
        assertNotNull(foundOrderItem);
        assertEquals(createdOrderItem.getId(), foundOrderItem.getId());
    }

    @Test
    @Order(6)
    void deleteById() {
        System.out.println("Delete By Id");
        OrderItem orderItem = new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(7)
                .setPrice(600.0)
                .setOrder(testOrder) // Link to the test order
                .build();

        OrderItem createdOrderItem = orderItemService.create(orderItem);
        System.out.println(createdOrderItem);
        orderItemService.deleteById(createdOrderItem.getId());

        OrderItem deletedOrderItem = orderItemService.read(createdOrderItem.getId());
        assertNull(deletedOrderItem);
    }
}
