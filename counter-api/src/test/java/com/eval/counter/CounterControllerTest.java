package com.eval.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class CounterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CounterService counterService;
	
	CounterVO counterVO1 = null;
	
	List<CounterVO> finalCount = null;
	List<String> list=null;
	
	Map<String,List<CounterVO>> finalOutput;
	int topVal;
	String finalValue;
	

	@Before
	public void initializeCounter()
	{
		list = new ArrayList<>();
		list.add("Duis");
		 counterVO1 = new CounterVO("Duis",11);
		 finalCount = new ArrayList<>();
		 finalCount.add(counterVO1);
		 finalOutput = new HashMap<>();
		 finalOutput.put("count", finalCount);
		 topVal = 5;
		 finalValue = "vel|17 eget|17 sed|16 in|15 et|14 ";
	}
	
	
	@Test
	public void searchCountTest() throws Exception 
	{
		when(
				counterService.getCount(list)).thenReturn(finalOutput);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/search").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		int responseCode = response.getStatus();
		 assertNotNull(response);
		  assertEquals(HttpStatus.OK.value(), responseCode);
		
	
	}
	
	
	@Test
	public void topValuesTest() throws Exception {
		when(
				counterService.getMaxCountWords(topVal)).thenReturn(finalValue);
		
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/top"+topVal).accept("text/csv");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		int responseCode = response.getStatus();
		 assertNotNull(response);
		  assertEquals(HttpStatus.OK.value(), responseCode);
		
		

		
	}

}
