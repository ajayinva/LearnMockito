package com.aces.learn.mockito.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {	
	private Map<Integer, String> repo = new HashMap<>();	
	
	private UserRepository() {
		repo.put(1, "Ajay");
		repo.put(2, "Vijay");
		repo.put(3, "Sanjay");
	}
	/***
	 * 
	 * @param id
	 * @param name
	 */
	public void add(Integer id, String name) {
		repo.put(id, name);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String get(Integer id) {
		return repo.get(id);
	}
}
