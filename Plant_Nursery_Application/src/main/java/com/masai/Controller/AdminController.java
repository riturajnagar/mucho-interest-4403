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
import com.masai.Exceptions.LoginException;
import com.masai.Service.AdminService;
import com.masai.Service.CustomerService;
import com.masai.Service.LoginService;
import com.masai.model.AdminDTO;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private LoginService lService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody AdminDTO dto) throws LoginException
	{
		String message = lService.logAdminIntoAccount(dto);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/logout/{key}")
	public ResponseEntity<String> logoutCustomer(@PathVariable("key") String key) throws LoginException
	{
		String message = lService.logOutAdminFromAccount(key);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/customers/{key}")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = adminService.addCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = adminService.upadateCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customers/{key}")
	public ResponseEntity<Customer> deleteCustomer(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = adminService.deleteCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.OK);
	}
	@GetMapping("/customers/{customerId}/{key}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("customerId") Integer id,@PathVariable("key") String key) throws LoginException, CustomerException
	{
		Customer customer = adminService.viewCustomer(id, key);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customers/{key}")
	public ResponseEntity<List<Customer> > viewAllCustomer(@PathVariable("key") String key) throws LoginException, CustomerException
	{
		List<Customer> customer = adminService.viewAllCustomers(key);
		
		return new ResponseEntity<List<Customer> >(customer,HttpStatus.ACCEPTED);
	}
	@GetMapping("/customers/{customerEmail}/{customerPassword}/{key}")
	public ResponseEntity<Customer> validateCustomer(@PathVariable("customerEmail") String email,@PathVariable("customerPassword") String password,@PathVariable("key") String key) throws LoginException, CustomerException
	{
		Customer customer = adminService.validateCustomer(email,password,key);
		
		return new ResponseEntity<Customer >(customer,HttpStatus.ACCEPTED);
	}
}
