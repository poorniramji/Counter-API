package com.eval.counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/counter-api")
public class CounterController {
	
	@Autowired
	private CounterService counterService;
	
	
	//Task 1 Text Count
	@RequestMapping(value = "/search", method = RequestMethod.POST, 
		    consumes = "application/json",produces = "application/json")
	public Map<String, List<CounterVO>> searchCount(@RequestBody Map<String,List<String>> searchInput) {
		Map<String,List<CounterVO>> finalOutputVal = null;
	
		List<String> inputVals = searchInput.get("searchText");
		
		
		if(inputVals.size() != 0) {
			finalOutputVal = counterService.getCount(inputVals);
			
		}
		
		return finalOutputVal;

	}
	
	
	//Task 2 Top N values
	@RequestMapping(value = "/top/{value}",produces = "text/csv")
	public String topValues(@PathVariable("value") int intValue) {
		String result = counterService.getMaxCountWords(intValue);
		return result;
		
	}
	
	

}
