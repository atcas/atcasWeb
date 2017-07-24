package com.atcas.models.graphTree;


public class Leaf {

	private String name;
	private Integer count;
	private Double factor;
	private Integer accuCount;
	private String externData;
	private String typeNode;
	
	
	public Leaf() {
		// TODO Auto-generated constructor stub
		this.factor=1.0;
		this.externData=null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getAccuCount() {
		return accuCount;
	}
	public void setAccuCount(Integer accuCount) {
		this.accuCount = accuCount;
	}
	public String getTypeNode() {
		return typeNode;
	}
	public void setTypeNode(String typeNode) {
		this.typeNode = typeNode;
	}

	
	
	
	public String getExternData() {
		return externData;
	}
	public void setExternData(String externData) {
		this.externData = externData;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	@Override
	public String toString() {
		return "\nLeaf [name=" + name + ", count=" + count + ", factor=" + factor + ", accuCount=" + accuCount
				+ ", externData=" + externData + ", typeNode=" + typeNode + "]";
	}
	

	
	
	
	
	
	
	
}
