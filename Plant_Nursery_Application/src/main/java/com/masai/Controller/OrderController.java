package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.OrderException;
import com.masai.Service.OrderService;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.OrdersDTO;

@RestController
@RequestMapping("/orders")
public class OrderController {
   
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders/{key}")
	public ResponseEntity<Orders> addOrder(@Valid @RequestBody OrdersDTO dto,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.addOrder(dto,key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@PutMapping("/orders/{orderId}/{key}")
	public ResponseEntity<Orders> updateOrder(@Valid @RequestBody OrdersDTO dto,@PathVariable("orderId") Integer id,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.updateOrder(dto,id,key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/orders/{orderId}/{key}")
	public ResponseEntity<Orders> deleteOrder(@PathVariable("orderId") Integer id,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.deleteOrder(id, key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{orderId}/{key}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("orderId") Integer id,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.viewOrder(id, key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{key}")
	public ResponseEntity<List<Orders> > viewAllOrder(@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		List<Orders> details = orderService.viewAllOrder(key);
		
		return new ResponseEntity<List<Orders> >(details,HttpStatus.CREATED);
	}
}
