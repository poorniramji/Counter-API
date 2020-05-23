package com.eval.counter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/counter-api")
public class CounterController {
	
	@Autowired
	private CounterService counterService;
	
	
	//Task 1 Text Count
	@RequestMapping(value = "/search", method = RequestMethod.POST, 
		    consumes = "application/json",produces = "application/json")
	@ResponseBody
	public Map<String, List<CounterVO>> searchCount(@RequestBody Map<String,List<String>> searchInput) {
		List<CounterVO> finalCount = null;
		Map<String,List<CounterVO>> finalOutput = new HashMap<>();
	
		List<String> inputVals = searchInput.get("searchText");
		
		
		if(inputVals.size() != 0) {
			finalCount = counterService.getCount(inputVals);
			finalOutput.put("count", finalCount);
		}
		
		return finalOutput;

	}
	
	
	//Task 2 Top N values
	@RequestMapping(value = "/top/{value}",produces = "application/json")
	@ResponseBody
	public Map<String, Integer> topValues(@PathVariable("value") int intValue) {
		Map<String, Integer> result = counterService.getMaxCountWords(intValue);
		return result;
		
	}
	

}
