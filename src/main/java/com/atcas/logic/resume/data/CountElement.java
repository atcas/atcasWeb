package com.atcas.logic.resume.data;

public class CountElement {
	private String nameContract;
	private boolean externalElement;
	private Integer count;
	private String typeElement;
	
	public CountElement() {
		// TODO Auto-generated constructor stub
		this.nameContract=null;
		this.externalElement=false;
		this.count=0;
		this.typeElement=null;
	}

	public String getNameContract() {
		return nameContract;
	}

	public void setNameContract(String nameContract) {
		this.nameContract = nameContract;
	}

	public boolean isExternalElement() {
		return externalElement;
	}

	public void setExternalElement(boolean externalElement) {
		this.externalElement = externalElement;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}
	
	
	
	
	
	

}
