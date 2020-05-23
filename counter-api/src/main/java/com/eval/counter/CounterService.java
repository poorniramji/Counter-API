package com.eval.counter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class CounterService {
	    
	  
	/*
	 * This method reads the input file and returns the content String
	 */
	public String readInputFile(){
		String content = null;
	
		try {
			File file = ResourceUtils.getFile("classpath:input/input.txt");
			content = new String(Files.readAllBytes(file.toPath()));
	         
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	         
		
		return content;
	}
	
	
	/*
	 * method Name : getCount
	 * This method is used to get the count of the text passed in the Request Body
	 */
	
	public List<CounterVO> getCount(List<String> list) {
		List<CounterVO> countList = new ArrayList<>();
		CounterVO counter = null;
		
		String actualContent = readInputFile();

			for(String value : list) {
				counter = new CounterVO();
				int i = 0;
				Pattern p = Pattern.compile(value,Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher( actualContent );
				while (m.find()) {
				    i++;
				}
				counter.setName(value);
				counter.settCount(i);
				countList.add(counter);
				
			}

		return countList;
	}
	
	
	
	/* method name : getMaxCountWords
	 * This method returns the sorted Linked Hashmap in key Value pair
	 */
	
	public Map<String, Integer> getMaxCountWords(int topValueNum) {
		Map<String, Integer> wordCount = new HashMap<>();
		Map<String, Integer> resultMap = new LinkedHashMap<>();
		
		String actualContent = readInputFile();
		String[] words = actualContent.split("[ \n\t\r.,;:!?(){}]");
		
	
		for (int counter = 0; counter < words.length; counter++) {
			
			String key = words[counter].toLowerCase();
			if (key.length() > 0) {
				
				if (wordCount.get(key) == null) 
				{
					wordCount.put(key, 1);
				} 
				else {
					
					int value = wordCount.get(key).intValue();
					value++;
					wordCount.put(key, value);
					
				}
			}
		}
		
		 Map<String, Integer> sortedMapDesc = sortByComparator(wordCount);
		

		 Set<Map.Entry<String, Integer>> entrySet = sortedMapDesc.entrySet();
		 for (Map.Entry<String, Integer> entry : entrySet) {
			 
				if(resultMap.size() >= topValueNum) 
					break;
				
				resultMap.put(entry.getKey(), entry.getValue());
				
			}

		return resultMap;
	}
	
	
	/* method name : sortByComparator
	 * This is used to sort the HashMap in descending order
	 */
	  private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap)
	    {
	        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

	        Collections.sort(list, new Comparator<Entry<String, Integer>>()
	        {
	            public int compare(Entry<String, Integer> o1,
	                    Entry<String, Integer> o2)
	            {
	               
	                    return o2.getValue().compareTo(o1.getValue());

	            }
	        });

	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Entry<String, Integer> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap;
	    }

}