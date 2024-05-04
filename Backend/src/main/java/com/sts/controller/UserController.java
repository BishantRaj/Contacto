package com.sts.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sts.exception.UserNotFoundException;
import com.sts.model.User;
import com.sts.repository.UserRepo;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	@Autowired
	private UserRepo repo;
	
	@PostMapping("/user")
	User newUser(@RequestBody User nu) {
		return repo.save(nu);
	}
	@GetMapping("/users")
	List<User> getAllUsers(){
		return repo.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return repo.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
		
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User nu,@PathVariable Long id) {
		return repo.findById(id)
				.map(user->{
					user.setUname(nu.getUname());
					user.setName(nu.getName());
					user.setEmail(nu.getEmail());
					return repo.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!repo.existsById(id))
			throw new UserNotFoundException(id);
		else {
			repo.deleteById(id);
			return "User with "+id+" has been deleted";
		}
	}
  	
}
