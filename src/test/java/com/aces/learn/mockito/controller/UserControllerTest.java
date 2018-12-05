package com.aces.learn.mockito.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aces.learn.mockito.service.UserService;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {
	
	@MockBean
	private UserService mockUserService;
	
	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;
	
	@Captor
	private ArgumentCaptor<Integer> integerArgumentCaptor;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String DUMMY_NAME = "Ajay";
	
	private static final String RESPONSE_JSON = "{id:1,name:Ajay}";
	
	private static final String REQUEST_JSON = "{\"id\":1,\"name\":\"Ajay\"}";

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void get_happy_path() throws Exception {
		when(mockUserService.getUser(Mockito.anyInt())).thenReturn(DUMMY_NAME);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/user/1")
				.accept(MediaType.APPLICATION_JSON);		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(RESPONSE_JSON))
				.andReturn();
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void add_happy_path() throws Exception {		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_JSON);		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(RESPONSE_JSON))
				.andReturn();		
		verify(mockUserService).addUser(integerArgumentCaptor.capture(),stringArgumentCaptor.capture());
		
		assertThat(integerArgumentCaptor.getValue(), is(1));
		assertThat(stringArgumentCaptor.getValue(), is(DUMMY_NAME));
	}

}
