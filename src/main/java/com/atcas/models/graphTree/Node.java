package com.atcas.models.graphTree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
    private String name;
    private Double factor;
    private List<Double> pieData;//":[40,60]
    private List<Object>children;
    private String typeNode;
    
    public Node() {
		// TODO Auto-generated constructor stub
    	this.factor=1.0;
    	this.children=new ArrayList<Object>();
    	
	}
    
    public void  addChild(Object node){
    	this.children.add(node);
    	
    }
	public String getTypeNode() {
		return typeNode;
	}
	public void setTypeNode(String typeNode) {
		this.typeNode = typeNode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	public List<Double> getPieData() {
		return pieData;
	}
	public void setPieData(List<Double> pieData) {
		this.pieData = pieData;
	}
	public List<Object> getChildren() {
		return children;
	}
	public void setChildren(List<Object> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		String main= "\nNode [name=" + name + ", factor=" + factor + ", pieData=" + pieData +
				", \nchildren=" + children
				+ ", typeNode=" + typeNode + "]\n";
		/*
		String children="[\n";
		for(int i=0;i<this.children.size();i++)
		{
			children=children+this.children.get(i).toString()+"\n";
		}
		children=children+"]\n";
		*/
		return main;
	}
    
	

}
