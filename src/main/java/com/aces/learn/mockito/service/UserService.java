package com.aces.learn.mockito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aces.learn.mockito.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository helloRepository;
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getUser(Integer id) {
		if(id==null) {
			throw new IllegalArgumentException("ID is required");
		}
		String name = helloRepository.get(id);
		if(name!=null) {
			String[] namesArray = new String[2];		
			for(int i = 0;i<2;i++) {
				namesArray[i] = name+i;
			}
			return String.join("-",namesArray);
		}
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void addUser(Integer id, String name) {
		if(id==null) {
			throw new IllegalArgumentException("ID is required");
		}
		if(name==null || "".equals(name)) {
			throw new IllegalArgumentException("Name is required");
		}
		Integer changedId = id + 1;
		String changedName = name+name;
		helloRepository.add(changedId, changedName);
	}
}
