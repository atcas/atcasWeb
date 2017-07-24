package com.atcas.logic.resume.data;

import java.util.HashMap;
import java.util.Map;

public class ElementResumeData {
	private String name;
	private Map<String, Integer> resume;
	
	public ElementResumeData() {
		// TODO Auto-generated constructor stub
		this.resume=new HashMap<String, Integer>();
	}
	
	
	
	public ElementResumeData(String name) {
		// TODO Auto-generated constructor stub
		this();
		this.name=name;
	}
		
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, Integer> getResume() {
		return resume;
	}
	
	
	public void addInfoResume(String data){
		if(this.resume.containsKey(data))
		{
			Integer count= this.resume.get(data)+1;
			this.resume.put(data, count);
		}
		else
		{
			
			this.resume.put(data, 1);
		}
	}

}
