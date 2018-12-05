package com.aces.learn.mockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aces.learn.mockito.dto.UserDto;
import com.aces.learn.mockito.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService helloService;
	@GetMapping("/hello/{id}")
	public UserDto getUser(
		@PathVariable String id
	) {
		String name =  helloService.getUser(Integer.parseInt(id));
		UserDto dto = new UserDto(id,name);
		return dto;
	}
}
