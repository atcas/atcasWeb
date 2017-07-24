package com.atcas.logic.resume.data;

import java.util.ArrayList;
import java.util.List;

public class Contract {
	private List<String> extendsContract;
	private List<String> varGlobal;
	private List<String> functionDeclaration;
	
	private List<String> functionInterface;
	boolean  hasFallback;
	private List<String> eventGlobal;
	private List<String> enunGlobal;
	private List<String> structGlobal;
	private List<String> modifierDeclaration;
	//List<String> functionAnonymous;
	private String name;
	
	
	
	public Contract(String name) {
		// TODO Auto-generated constructor stub
		this.extendsContract=new ArrayList<String>();
		this.varGlobal= new ArrayList<String>();
		this.enunGlobal= new ArrayList<String>();
		this.functionDeclaration= new ArrayList<String>();
		//this.functionAnonymous= new ArrayList<String>();
		this.functionInterface= new ArrayList<String>();
		this.eventGlobal= new ArrayList<String>();
		this.structGlobal= new ArrayList<String>();
		this.modifierDeclaration= new ArrayList<String>();
		this.hasFallback=false;
		this.name=name;
	}

	
	public List<String> getExtendsContract() {
		return extendsContract;
	}
	
	


	public void setHasFallback(boolean hasFallback) {
		this.hasFallback = hasFallback;
	}



	


	public List<String> getEnunGlobal() {
		return enunGlobal;
	}





	public String getName() {
		return name;
	}





	public List<String> getVarGlobal() {
		return varGlobal;
	}



	public List<String> getFunctionDeclaration() {
		return functionDeclaration;
	}



	public List<String> getFunctionInterface() {
		return functionInterface;
	}



	public boolean isHasFallback() {
		return hasFallback;
	}



	public List<String> getEventGlobal() {
		return eventGlobal;
	}



	public List<String> getStructGlobal() {
		return structGlobal;
	}



	public List<String> getModifierDeclaration() {
		return modifierDeclaration;
	}
	
	
	
	


}
