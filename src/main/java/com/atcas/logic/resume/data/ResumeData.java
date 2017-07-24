package com.atcas.logic.resume.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeData {
	
	public static final String CONTRC_ELEM="CONTRC";
	public static final String VAR_ELEM="VAR";
	public static final String FNT_ELEM="FNT";
	public static final String MODF_ELEM="MOD";
	public static final String STRUCT_ELEM="STRUCT";
	public static final String ENUN_ELEM="ENUM";
	public static final String EVENT_ELEM="EVENT";
	
	private String name;
	
	private Map<String,Map<String,CountElement>> functionDeclarationVarGlobal;
	private Map<String,Map<String,CountElement>> functionDeclarationEventGlobal;
	private Map<String,Map<String,CountElement>> functionDeclarationEnunGlobal;
	private Map<String,Map<String,CountElement>> functionDeclarationStructGlobal;
	private Map<String,Map<String,CountElement>> functionDeclarationModifierDeclaration;
	private Map<String,Map<String,CountElement>> functionDeclarationFunctionDeclaration;
	
	
	
	
	
	private Map<String,CountElement> functionFallBackDeclarationVarGlobal;
	private Map<String,CountElement>  functionFallBackDeclarationEventGlobal;
	private Map<String,CountElement> functionFallBackDeclarationEnunGlobal;
	private Map<String,CountElement>  functionFallBackDeclarationStructGlobal;
	private Map<String,CountElement>  functionFallBackDeclarationModifierDeclaration;
	
	
	private Map<String,Map<String,CountElement>> modifierDeclarationVarGlobal;
	private Map<String,Map<String,CountElement>> modifierDeclarationEventGlobal;
	private Map<String,Map<String,CountElement>> modifierDeclarationEnunGlobal;
	private Map<String,Map<String,CountElement>> modifierDeclarationStructGlobal;
	
	private Map<String,Map<String,CountElement>> modifierDeclarationModifierDeclaration;
	
	private Map<String,Map<String,CountElement>> modifierDeclarationFunctionDeclaration;
	
	
	private Contract contract;
	
	private List<Contract> listContract;
	
	public Contract getContract() {
		return contract;
	}
	
	
	public ResumeData(Contract contract,List<Contract> listContract) {
		// TODO Auto-generated constructor stub
		this.contract=contract;
		this.listContract=listContract;
		this.name=this.contract.getName();
		this.functionDeclarationVarGlobal=new HashMap<String,Map<String,CountElement>>();
		this.functionDeclarationEventGlobal=new HashMap<String,Map<String,CountElement>>();
		this.functionDeclarationEnunGlobal=new HashMap<String,Map<String,CountElement>>();
		this.functionDeclarationStructGlobal=new HashMap<String,Map<String,CountElement>>();
		this.functionDeclarationModifierDeclaration=new HashMap<String,Map<String,CountElement>>();
		this.functionDeclarationFunctionDeclaration=new HashMap<String,Map<String,CountElement>>();
		for(int i =0;i< this.contract.getFunctionDeclaration().size();i++)
		{
			String name= this.contract.getFunctionDeclaration().get(i);
			this.functionDeclarationVarGlobal.put(name, null);
			this.functionDeclarationEventGlobal.put(name, null);
			this.functionDeclarationEnunGlobal.put(name, null);
			this.functionDeclarationStructGlobal.put(name, null);
			this.functionDeclarationModifierDeclaration.put(name, null);
			this.functionDeclarationFunctionDeclaration.put(name, null);
			
			
		}
		
		
		this.functionFallBackDeclarationVarGlobal=new HashMap<String,CountElement>();
		this.functionFallBackDeclarationEventGlobal=new HashMap<String,CountElement>();
		this.functionFallBackDeclarationEnunGlobal=new  HashMap<String,CountElement>();
		this.functionFallBackDeclarationStructGlobal=new HashMap<String,CountElement>();
		this.functionFallBackDeclarationModifierDeclaration=new HashMap<String,CountElement>();
		
	
		
		this.modifierDeclarationVarGlobal=new HashMap<String,Map<String,CountElement>>();
		this.modifierDeclarationEventGlobal=new HashMap<String,Map<String,CountElement>>();
		this.modifierDeclarationEnunGlobal=new HashMap<String,Map<String,CountElement>>();
		this.modifierDeclarationStructGlobal=new HashMap<String,Map<String,CountElement>>();
		
		this.modifierDeclarationModifierDeclaration=new HashMap<String,Map<String,CountElement>>();
		
		this.modifierDeclarationFunctionDeclaration=new HashMap<String,Map<String,CountElement>>();
		
		for(int i =0;i< this.contract.getModifierDeclaration().size();i++)
		{
			String name= this.contract.getModifierDeclaration().get(i);
			this.modifierDeclarationVarGlobal.put(name, null);
			this.modifierDeclarationEventGlobal.put(name, null);
			this.modifierDeclarationEnunGlobal.put(name, null);
			this.modifierDeclarationStructGlobal.put(name, null);
			this.modifierDeclarationModifierDeclaration.put(name, null);
			
			this.modifierDeclarationFunctionDeclaration.put(name, null);
					
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	
	public CountElement getValueModifierDeclarationFunctionDeclaration(String fn,String varGlobal){
		return this.modifierDeclarationFunctionDeclaration.get(fn).get(varGlobal);			
	}
	
	
	
	public List<String> getListKeyModifierDeclarationFunctionDeclaration(String fn){
			
		if(this.modifierDeclarationFunctionDeclaration.get(fn)!=null)
		{
			return new ArrayList<String>(this.modifierDeclarationFunctionDeclaration.get(fn).keySet());			

		}
		return null;
	
	}
	
	
	
	
	
	
	
	public CountElement getValueModifierDeclarationEventGlobal(String fn,String varGlobal){
		return this.modifierDeclarationEventGlobal.get(fn).get(varGlobal);			
	}
	
	
	
	public List<String> getListKeyModifierDeclarationEventGlobal(String fn){
			
		if(this.modifierDeclarationEventGlobal.get(fn)!=null)
		{
			return new ArrayList<String>(this.modifierDeclarationEventGlobal.get(fn).keySet());			

		}
		return null;
	
	}
	
	
	
	
	
	
	//private Map<String,Map<String,Integer>> modifierDeclarationVarGlobal;
	
	
	
	
	
	
	public CountElement getValueModifierDeclarationVarGlobal(String fn,String varGlobal){
		return this.modifierDeclarationVarGlobal.get(fn).get(varGlobal);			
	}
	
	
	
	public List<String> getListKeyModifierDeclarationVarGlobal(String fn){
			
		if(this.modifierDeclarationVarGlobal.get(fn)!=null)
		{
			return new ArrayList<String>(this.modifierDeclarationVarGlobal.get(fn).keySet());			

		}
		return null;
	
	}
	

	/*
	private Map<String,Map<String,Integer>> functionDeclarationVarGlobal;
	private Map<String,Map<String,Integer>> functionDeclarationEventGlobal;
	private Map<String,Map<String,Integer>> functionDeclarationEnunGlobal;
	private Map<String,Map<String,Integer>> functionDeclarationStructGlobal;
	private Map<String,Map<String,Integer>> functionDeclarationModifierDeclaration;
	*/
	
	
	
	
	public CountElement getValueFunctionDeclarationFucntionDeclaration(String fn,String varGlobal){
		return this.functionDeclarationFunctionDeclaration.get(fn).get(varGlobal);			
	}
	
	
	
	
	public CountElement getValueFunctionDeclarationModifierDeclaration(String fn,String varGlobal){
		return this.functionDeclarationModifierDeclaration.get(fn).get(varGlobal);			
	}
	
	
	public CountElement getValueFunctionDeclarationEventGlobal(String fn,String varGlobal){
		return this.functionDeclarationEventGlobal.get(fn).get(varGlobal);	
	}
	
	
	public CountElement getValueFunctionDeclarationVarGlobal(String fn,String varGlobal){
		return this.functionDeclarationVarGlobal.get(fn).get(varGlobal);			
	}
	
	
	
	
	public List<String> getListKeyFunctionDeclarationFunctionDeclaration(String fn){
		if(this.functionDeclarationFunctionDeclaration.get(fn)!=null)
		{
			return new ArrayList<String>(this.functionDeclarationFunctionDeclaration.get(fn).keySet());			

		}
		return null;
	}
	
	
	
	public List<String> getListKeyFunctionDeclarationModifierDeclaration(String fn){
		if(this.functionDeclarationModifierDeclaration.get(fn)!=null)
		{
			return new ArrayList<String>(this.functionDeclarationModifierDeclaration.get(fn).keySet());			

		}
		return null;
	}
	
	
	public List<String> getListKeyFunctionDeclarationEventGlobal(String fn){
		
		if(this.functionDeclarationEventGlobal.get(fn)!=null)
		{
			return new ArrayList<String>(this.functionDeclarationEventGlobal.get(fn).keySet());			

		}
		return null;
		
		
		
	}
	
	
	public List<String> getListKeyFunctionDeclarationVarGlobal(String fn){
		
		if(this.functionDeclarationVarGlobal.get(fn)!=null )
		{
			return new ArrayList<String>(this.functionDeclarationVarGlobal.get(fn).keySet());			

		}
		return null;
	}
	
	public void addInfoFunctionDeclarationGeneral(String name,String data){
	
		if(addInfoFunctionDeclarationVarGlobal(name,data))
		{
			return ;
		}
		
		if(addInfoFunctionDeclarationFunctionDeclaration(name,data))
		{
			return ;
		}
		
		if(addInfoFunctionDeclarationEventGlobal(name,data))
		{
			return ;
		}
	}
	public boolean addInfoFunctionDeclarationVarGlobal(String name,String data){
		String contractBase=null;
		if(this.contract.getVarGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getVarGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		
		if(this.functionDeclarationVarGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.functionDeclarationVarGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{

				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(VAR_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(VAR_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			
			//temp.put(data, 1);
			this.functionDeclarationVarGlobal.put(name, temp);
		}
		return true;
	}
	
	public boolean addInfoFunctionDeclarationEventGlobal(String name,String data){
		
		String contractBase=null;
		
		if(this.contract.getEventGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getEventGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		if(this.functionDeclarationEventGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.functionDeclarationEventGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(EVENT_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(EVENT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			//--temp.put(data, 1);
			this.functionDeclarationEventGlobal.put(name, temp);
		}
		
		return true;
		
	}
	
	
	public void addInfoFunctionDeclarationEnunGlobal(String name,String data){
	
		String contractBase=null;
		if(this.contract.getEnunGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getEnunGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		
		
		if(this.functionDeclarationEnunGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.functionDeclarationEnunGlobal.get(name);
			if(temp.get(data)!=null)
			{
				
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(ENUN_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(ENUN_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			
			//--temp.put(data, 1);
			this.functionDeclarationEnunGlobal.put(name, temp);
		}
		
	
	}
	
	
	public boolean addInfoFunctionDeclarationStructGlobal(String name,String data){
		
		String contractBase=null;
		if(this.contract.getStructGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getStructGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		if(this.functionDeclarationStructGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.functionDeclarationStructGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(STRUCT_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(STRUCT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			//--temp.put(data, 1);
			this.functionDeclarationStructGlobal.put(name, temp);
		}
		return true;
	
	}
	
	
	public boolean addInfoFunctionDeclarationFunctionDeclaration(String name,String data){
		
		String contractBase=null;
		if(this.contract.getFunctionDeclaration().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getFunctionDeclaration().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		if(this.functionDeclarationFunctionDeclaration.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.functionDeclarationFunctionDeclaration.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(FNT_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(FNT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			//--temp.put(data, 1);
			this.functionDeclarationFunctionDeclaration.put(name, temp);
		}
		return true;
		
	}

	
	
	public void addInfoFunctionDeclarationModifierDeclaration(String name,String data){
		
		String contractBase=null;
		if(this.contract.getModifierDeclaration().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getModifierDeclaration().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		
		if(this.functionDeclarationModifierDeclaration.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.functionDeclarationModifierDeclaration.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(MODF_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(MODF_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			//--temp.put(data, 1);
			this.functionDeclarationModifierDeclaration.put(name, temp);
		}
		
		
	}
	
	
	
	
		
	public void addInfoFunctionFallBackDeclarationVarGlobal(String data){
		
		String contractBase=null;
		if(this.contract.getVarGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getVarGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		if(this.functionFallBackDeclarationVarGlobal.get(data)!=null)
		{
			CountElement countElement= this.functionFallBackDeclarationVarGlobal.get(data);//+1;
			countElement.setCount(countElement.getCount()+1);
			
			this.functionFallBackDeclarationVarGlobal.put(data, countElement);
		}
		else
		{
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(VAR_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			
			this.functionFallBackDeclarationVarGlobal.put(data, countElement);
		}
	}
	
	public void addInfoFunctionFallBackDeclarationEventGlobal(String data){
		String contractBase=null;
		if(this.contract.getEventGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getEventGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		if(this.functionFallBackDeclarationEventGlobal.get(data)!=null)
		{
			CountElement countElement=this.functionFallBackDeclarationEventGlobal.get(data);//+1;
			countElement.setCount(countElement.getCount()+1);
			
			
			this.functionFallBackDeclarationEventGlobal.put(data, countElement);
		}
		else
		{
			
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(EVENT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			
			this.functionFallBackDeclarationEventGlobal.put(data, countElement);
		}
	}
	
	public void addInfoFunctionFallBackDeclarationEnunGlobal(String data){
		
		String contractBase=null;
		if(this.contract.getEnunGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getEnunGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		
		if(this.functionFallBackDeclarationEnunGlobal.get(data)!=null)
		{
			CountElement countElement=  this.functionFallBackDeclarationEnunGlobal.get(data);//+1;
			countElement.setCount(countElement.getCount()+1);
			this.functionFallBackDeclarationEnunGlobal.put(data, countElement);
		}
		else
		{
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(ENUN_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			
			this.functionFallBackDeclarationEnunGlobal.put(data, countElement);
		}
	}
	
	
	public void addInfoFunctionFallBackDeclarationStructGlobal(String data){
		String contractBase=null;
		if(this.contract.getStructGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getStructGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		if(this.functionFallBackDeclarationStructGlobal.get(data)!=null)
		{
			CountElement countElement=  this.functionFallBackDeclarationStructGlobal.get(data);//+1;
			countElement.setCount(countElement.getCount()+1);
			this.functionFallBackDeclarationStructGlobal.put(data, countElement);
		}
		else
		{
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(STRUCT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			this.functionFallBackDeclarationStructGlobal.put(data, countElement);
		}
	}
	

	
	public void addInfoFunctionFallBackDeclarationModifierDeclaration(String data){
		
		String contractBase=null;
		if(this.contract.getModifierDeclaration().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getModifierDeclaration().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		if(this.functionFallBackDeclarationModifierDeclaration.get(data)!=null)
		{
			
		
			
			CountElement countElement= this.functionFallBackDeclarationModifierDeclaration.get(data);//+1;
			
			countElement.setCount(countElement.getCount()+1);
			
			
			this.functionFallBackDeclarationModifierDeclaration.put(data, countElement);
		}
		else
		{
			
			
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(MODF_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			
			this.functionFallBackDeclarationModifierDeclaration.put(data, countElement);
		}
	}
	
	
	
	public void addInfoModifierDeclarationGeneral(String name,String data){
		
		if(addInfoModifierDeclarationVarGlobal(name,data))
		{
			return ;
		}
		
		if(addInfoModifierDeclarationFunctionDeclaration(name,data))
		{
			return ;
		}
		
		if(addInfoModifierDeclarationEventGlobal(name,data))
		{
			return ;
		}
		
	}


	
	public boolean addInfoModifierDeclarationVarGlobal(String name,String data){
		String contractBase=null;
		if(this.contract.getVarGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getVarGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		if(this.modifierDeclarationVarGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.modifierDeclarationVarGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(VAR_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(VAR_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			//--temp.put(data, 1);
			this.modifierDeclarationVarGlobal.put(name, temp);
		}
		return true;
		
	}

	public boolean addInfoModifierDeclarationEventGlobal(String name,String data){
		
		String contractBase=null;
		if(this.contract.getEventGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getEventGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return true;
			}
		}
		
		
		
		if(this.modifierDeclarationEventGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.modifierDeclarationEventGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(EVENT_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
			
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(EVENT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			//--temp.put(data, 1);
			this.modifierDeclarationEventGlobal.put(name, temp);
		}
		return true;
		
	}
	
	public void addInfoModifierDeclarationEnunGlobal(String name,String data){
		
		String contractBase=null;
		if(this.contract.getEnunGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getEnunGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		if(this.modifierDeclarationEnunGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.modifierDeclarationEnunGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(ENUN_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(ENUN_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			
			//--temp.put(data, 1);
			this.modifierDeclarationEnunGlobal.put(name, temp);
		}
		
		
	
	}
	
	
	//modifierDeclarationFunctionDeclaration
	
	public boolean addInfoModifierDeclarationFunctionDeclaration(String name,String data){
		String contractBase=null;
		if(this.contract.getFunctionDeclaration().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getFunctionDeclaration().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		if(this.modifierDeclarationFunctionDeclaration.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.modifierDeclarationFunctionDeclaration.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(FNT_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(FNT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			
			
			//--temp.put(data, 1);
			this.modifierDeclarationFunctionDeclaration.put(name, temp);
		}
		return true;
		
		
	}
	
	//modifierDeclarationModifierDeclaration
	
	public void addInfoModifierDeclarationModifierDeclaration(String name,String data){
		String contractBase=null;
		if(this.contract.getModifierDeclaration().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getModifierDeclaration().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return;
			}
		}
		
		
		if(this.modifierDeclarationModifierDeclaration.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.modifierDeclarationModifierDeclaration.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(MODF_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(MODF_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			
			
			//--temp.put(data, 1);
			this.modifierDeclarationModifierDeclaration.put(name, temp);
		}
		
		
		
	}
	
	
	public boolean addInfoModifierDeclarationStructGlobal(String name,String data){
		String contractBase=null;
		if(this.contract.getStructGlobal().indexOf(data) <0)
		{

			
			for(int i =0; i<this.listContract.size();i++)
			{
				if(!this.listContract.get(i).getName().equals(this.name)
				  && this.listContract.get(i).getStructGlobal().indexOf(data)>-1
				 ){
					contractBase=this.listContract.get(i).getName();
					break;
				}
			}
			if(contractBase==null)
			{
				return false;
			}
		}
		
		
		if(this.modifierDeclarationStructGlobal.get(name)!=null)
		{
			Map<String,CountElement>  temp=this.modifierDeclarationStructGlobal.get(name);
			if(temp.get(data)!=null)
			{
				CountElement countElement= temp.get(data);
				countElement.setCount(countElement.getCount()+1);
				
				//CountElement countElement= temp.get(data)+1;
				temp.put(data, countElement);
				//--Integer count= temp.get(data)+1;
				//--temp.put(data, count);
				//this.functionDeclarationVarGlobal.put(name, temp);
			}
			else
			{
				CountElement countElement= new CountElement();
				countElement.setCount(1);
				countElement.setTypeElement(STRUCT_ELEM);
				if(contractBase!=null)
				{
					countElement.setExternalElement(true);
					countElement.setNameContract(contractBase);
				}
				temp.put(data, countElement);
				//--temp.put(data, 1);
			}
			//Integer count= this.functionDeclarationVarGlobal.get(data)+1;
			//this.functionDeclarationVarGlobal.put(data, count);
		}
		else
		{
			Map<String,CountElement>  temp=new HashMap<String,CountElement>();
			CountElement countElement= new CountElement();
			countElement.setCount(1);
			countElement.setTypeElement(STRUCT_ELEM);
			if(contractBase!=null)
			{
				countElement.setExternalElement(true);
				countElement.setNameContract(contractBase);
			}
			temp.put(data, countElement);
			
			
			//--temp.put(data, 1);
			this.modifierDeclarationStructGlobal.put(name, temp);
		}
		
		
		return true;
	}
	
}
