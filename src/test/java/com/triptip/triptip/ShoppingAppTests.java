package com.triptip.triptip;

import com.triptip.triptip.model.*;
import com.triptip.triptip.repository.*;
import com.triptip.triptip.service.AddressService;
import com.triptip.triptip.service.OrderItemService;
import com.triptip.triptip.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShoppingAppTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

	@Test
	void contextLoads() {

	}

	private User createUser(String login, String password){
		return userRepository.save(new User(login,password));
	}
	@Test
	void testSaveTwoUsers() {
		// Given
		User user1 = new User("adam", "nowak");
		User user2 = new User("jan", "kowalski");

		// When
		userRepository.save(user1);
		userRepository.save(user2);

		// Then
		User savedUser1 = userRepository.findByLogin("adam");
		User savedUser2 = userRepository.findByLogin("jan");

		assertNotNull(savedUser1);
		assertNotNull(savedUser2);
		assertEquals("nowak", savedUser1.getPassword());
		assertEquals("kowalski", savedUser2.getPassword());
	}
	@Test
	void testListAllUsers() {
		List<User> userList = userRepository.findAll();
		// Display all users
		System.out.println("All Users:");
		for (User user : userList) {
			System.out.println("User: " + user);
		}

	}
    @Test
    void findUser(){
        User user = userRepository.findByLogin("user1");
        System.out.println(user);
    }
	@Test
	void testListAllOrders(){
		List<Order> orderList = orderRepository.findAll();
		System.out.println("Orders: \n");
		int i=0;
		for(Order order : orderList){
			System.out.println("Order: " + i + " - " + order);
			i++;
		}
	}
	//Użytkownika według maila
	@Test
	void findUserByMail(){
		String email = "user1@o2.pl";
		List<User> userList = userRepository.findAll();
		for(User user:userList){
			if(user.getEmail().contains(email))System.out.println(user);
		}

	}
	//Liczba transakcji
	@Test
	void countOrders(){
		List<Order> orderList = orderRepository.findAll();

		int i=0;
		for(Order order : orderList){
			i++;
		}
		System.out.println("Number of transactions: " + i);
	}
	//Liczba transakcji konkretnego użytkownika
	@Test
	public void countTransactionByUsername(){
		String username = "user1";
		List<Order> orderList = orderRepository.findAll();
		int count=0;
		for(Order order: orderList){
			if(order.getUser().getLogin().contains(username))count++;
		}
		System.out.println("Numer of transactions for user "+username + " is " +count);
	}

	//Liczba transakcji konkretnego produktu
	@Test
	public void countTransactionsByProductName() {
		String productName = "Amazon";
		List<OrderItem> orderItemList = orderItemRepository.findAll();

		// Group OrderItems by orderId
		Map<Integer, List<OrderItem>> orderItemMap = new HashMap<>();
		for (OrderItem orderItem : orderItemList) {
			int orderId = orderItem.getOrderId();
			orderItemMap.computeIfAbsent(orderId, k -> new ArrayList<>()).add(orderItem);
		}


		int i=0;
	//	int check=0;
		for (List<OrderItem> orderItems : orderItemMap.values()) {

			for (OrderItem orderItem : orderItems) {
				if(orderItem.getProduct().getProductName().contains(productName)){
					i++;
					System.out.println("Order Item: " + orderItem);
					break;
				}
			}
		}
		int numberOfLists = orderItemMap.size();
		System.out.println("Number of lists: " + i);
	}
	@Test
	public void listProducts(){
		System.out.println(productRepository.findAll());
	}
	@Test
	public void getProductByPrice(){
		int price1=900,price2=1100;
		List<Product> productList = productRepository.findAll();
		for(Product product:productList){
			if(product.getPrice()>=price1 && product.getPrice() <=price2)
					System.out.println(product);
		}
	}

    @Test
    public void addOrderItems(){
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(302, productRepository.findById(3), 2)); // Order ID: 1, Product 1, Quantity: 2
        orderItems.add(new OrderItem(302,  productRepository.findById(2), 1)); // Order ID: 1, Product 2, Quantity: 1
//        System.out.println(orderItems);
        orderItemService.addOrderItems(orderItems);

    }
}
