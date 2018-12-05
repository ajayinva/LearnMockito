package com.aces.learn.mockito.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import org.mockito.Mockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.aces.learn.mockito.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {	
	@Mock
	private UserRepository mockUserRepository;
	
	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;
	
	@Captor
	private ArgumentCaptor<Integer> integerArgumentCaptor;
	
	@InjectMocks
	private UserService userService;
	
	private static final String DUMMY_NAME = "Ajay";
	
	@Test(expected = IllegalArgumentException.class)
	public void getUser_input_id_is_null() {	
		userService.getUser(null);			
	}

	@Test
	public void getUser_happy_path() {	
		when(mockUserRepository.get(Mockito.anyInt())).thenReturn(DUMMY_NAME);
		String actualValue = userService.getUser(1);		
		assertThat(actualValue, is("Ajay0-Ajay1"));
	}
	
	@Test
	public void getUser_no_user_in_db() {	
		when(mockUserRepository.get(Mockito.anyInt())).thenReturn(null);
		String actualValue = userService.getUser(1);		
		assertThat(actualValue, nullValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addUser_id_is_null() {	
		userService.addUser(null, "");			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addUser_name_is_null() {	
		userService.addUser(1, null);			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addUser_name_is_empty() {	
		userService.addUser(1, "");			
	}
	
	@Test
	public void addUser_happy_path() {	
		userService.addUser(1, DUMMY_NAME);		
		verify(mockUserRepository).add(Mockito.anyInt(), Mockito.anyString());
		verify(mockUserRepository).add(integerArgumentCaptor.capture(),stringArgumentCaptor.capture());
		assertThat(integerArgumentCaptor.getValue(), is(2));
		assertThat(stringArgumentCaptor.getValue(), is(DUMMY_NAME+DUMMY_NAME));
	}
}
