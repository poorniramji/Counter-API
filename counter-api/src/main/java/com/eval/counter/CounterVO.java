package com.eval.counter;

import org.springframework.stereotype.Component;

@Component
public class CounterVO {
	

	String name;
	Integer tCount;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the tCount
	 */
	public Integer gettCount() {
		return tCount;
	}
	/**
	 * @param tCount the tCount to set
	 */
	public void settCount(Integer tCount) {
		this.tCount = tCount;
	}
	
	@Override
	public String toString() {
		return "CounterVO [name=" + name + ", tCount=" + tCount + "]";
	}

}
