package com.aces.learn.mockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aces.learn.mockito.dto.UserDto;
import com.aces.learn.mockito.service.UserService;
/**
 * 
 * @author ajay.agarwal
 *
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public UserDto get(
		@PathVariable String id
	) {
		Integer intId = Integer.parseInt(id);
		String name =  userService.getUser(intId);
		UserDto dto = new UserDto(intId,name);
		return dto;
	}
	/**
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/user")
	public UserDto add(
		@RequestBody 
		UserDto dto
	) {
		userService.addUser(dto.getId(), dto.getName());
		return dto;
	}
}
