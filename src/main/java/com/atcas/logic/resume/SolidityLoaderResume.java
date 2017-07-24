package com.atcas.logic.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.RuleContext;

import com.atcas.grammar.antrl.solidity.base.SolidityBaseListener;
import com.atcas.grammar.antrl.solidity.base.SolidityParser;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.ContractElementContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.FunctionDeclarationContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.IdentifierExpressionContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.ModifierDeclarationContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.ModifierExtendFunctionConstructorContext;
import com.atcas.logic.resume.data.Contract;
import com.atcas.logic.resume.data.ResumeData;


public class SolidityLoaderResume  extends SolidityBaseListener {
	

	
	
	Map<String, ResumeData> resumeData;
	class DataContractElement{
		public String nameContract;
		public String nameElement;
		public String nameIdentifier;
		public Integer typeElement;
		public DataContractElement(String nameIdentifier) {
			// TODO Auto-generated constructor stub
			this(nameIdentifier,null);//=null;
			this.nameIdentifier=nameIdentifier;
		}
		public DataContractElement(String nameIdentifier,Integer typeElement) {
			// TODO Auto-generated constructor stub
			this.typeElement=typeElement;
			this.nameIdentifier=nameIdentifier;
		}
	}
	
	class DataChainObject{
		public String nameMain;
		public List<String> nameElement;
		public List<Integer> typeElement;
		
		public DataChainObject(String nameElement) {
			// TODO Auto-generated constructor stub
			this.typeElement=null;
			this.nameElement=new ArrayList<String>();
		}
	}
	
	private List<Contract> getExtendsContracts( Contract cBase, Map<String, Contract> mapContracts)
	{
		List<Contract> queue= new ArrayList<Contract>();
		List<Contract> extContracts=new ArrayList<Contract>();
		
		for(int i=0;i<cBase.getExtendsContract().size();i++)
		{
			queue.add(mapContracts.get(cBase.getExtendsContract().get(i)));
			extContracts.add(mapContracts.get(cBase.getExtendsContract().get(i)));
		}
		
		while(queue.size()>0)
		{
			Contract cTemp=queue.remove(0);
			if(cTemp.getExtendsContract().size()>0)
			{
				for(int i=0;i<cTemp.getExtendsContract().size();i++)
				{
					queue.add(mapContracts.get(cTemp.getExtendsContract().get(i)));
					extContracts.add(mapContracts.get(cTemp.getExtendsContract().get(i)));
				}
				
			}
		}

		
		return extContracts;
	}
	
	
	@Override
	public void exitModifierExtendFunctionConstructor(ModifierExtendFunctionConstructorContext ctx) {
		// TODO Auto-generated method stub
		//super.exitModifierExtendFunctionConstructor(ctx);
		
		
		DataContractElement data =getContractElement(new DataContractElement(ctx.Identifier().getText()),ctx.getPayload());


		ResumeData rsD =this.resumeData.get(data.nameContract);
		

		switch (data.typeElement) {
		case SolidityParser.RULE_functionDeclaration:
			rsD.addInfoFunctionDeclarationModifierDeclaration(data.nameElement, data.nameIdentifier);
			System.out.println("RULE_functionDeclaration:["+data.nameElement+"]"+ data.nameIdentifier);
			break;

			
		case SolidityParser.RULE_modifierDeclaration:
			rsD.addInfoModifierDeclarationModifierDeclaration(data.nameElement, data.nameIdentifier);
			System.out.println("RULE_modifierDeclaration:["+data.nameElement+"]"+ data.nameIdentifier);

			break;
		case SolidityParser.RULE_functionDeclarationInterface:
			//System.out.println("RULE_functionDeclarationInterface:"+ data.nameIdentifier);
			System.out.println("RULE_functionDeclarationInterface:["+data.nameElement+"]"+ data.nameIdentifier);

			break;
			
		default:
			System.out.println("varGlobal:"+ data.nameIdentifier);
			break;
		}


		
		
	}
	
	public SolidityLoaderResume(Map<String, Contract> mapContracts
			/*List<Contract> contracts*/) {
		// TODO Auto-generated constructor stub
		List<Contract> contracts= new ArrayList<Contract>(mapContracts.values());
		this.resumeData=new HashMap<String, ResumeData>();
		for(int i =0;i< contracts.size();i++)
		{
			Contract c= contracts.get(i);
			ResumeData rd= new ResumeData(c,getExtendsContracts(c,mapContracts));
			System.out.println("contar:"+c.getName());
			this.resumeData.put(c.getName(), rd);
		}
	}
	
	public List<ResumeData> getListResumeData(){
		return new ArrayList<ResumeData>(this.resumeData.values());
	}
	
	@Override
	public void enterIdentifierExpression(IdentifierExpressionContext ctx) {
		// TODO Auto-generated method stub
		//super.enterIdentifierExpression(ctx);
		
		if(ctx.Identifier()!=null)
		{
			//System.out.println("pase:"+ctx.Identifier().getText());
		

			DataContractElement data =getContractElement(new DataContractElement(ctx.Identifier().getText()),ctx.getPayload());
			//--ResumeData rd=this.resumeData.get(data.nameContract);
		
		//	if(data.nameIdentifier.equals("msg"))
			//{
				
				//System.out.println(ctx.getParent().getRuleIndex() +"--"+ SolidityParser.RULE_singleExpression+"--"+ctx.getParent().getChild(1).getText());
			//}
			
			
	
			ResumeData rsD =this.resumeData.get(data.nameContract);
			
			//if(data.typeElement==null)

			switch (data.typeElement) {
			case SolidityParser.RULE_functionDeclaration:
				rsD.addInfoFunctionDeclarationGeneral(data.nameElement, data.nameIdentifier);
				System.out.println("RULE_functionDeclaration:["+data.nameElement+"]"+ data.nameIdentifier);
				break;

				
			case SolidityParser.RULE_modifierDeclaration:
				rsD.addInfoModifierDeclarationGeneral(data.nameElement, data.nameIdentifier);
				System.out.println("RULE_modifierDeclaration:["+data.nameElement+"]"+ data.nameIdentifier);

				break;
			case SolidityParser.RULE_functionDeclarationInterface:
				//System.out.println("RULE_functionDeclarationInterface:"+ data.nameIdentifier);
				System.out.println("RULE_functionDeclarationInterface:["+data.nameElement+"]"+ data.nameIdentifier);

				break;
				
			default:
				System.out.println("varGlobal:"+ data.nameIdentifier);
				break;
			}
	
	
		
		}
	
		
		
		
	}
	
	

	
/*

	private DataContractElement getFunctionModElement(DataContractElement temp,RuleContext rlc){
	
		if(rlc.getRuleIndex()==SolidityParser.RULE_functionDeclaration)
		{
			
			FunctionDeclarationContext fn=(FunctionDeclarationContext) rlc;
			temp.nameElement=fn.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_functionDeclaration;
			return getContractElement(temp, rlc.getParent());
			
		}
		else if(rlc.getRuleIndex()==SolidityParser.RULE_functionDeclarationInterface)
		{
			
			FunctionDeclarationInterfaceContext fn=(FunctionDeclarationInterfaceContext) rlc;
			temp.nameElement=fn.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_functionDeclarationInterface;
			return getContractElement(temp, rlc.getParent());
			
		}
		
		else if(rlc.getRuleIndex()==SolidityParser.RULE_functionDeclarationFallBack)
		{
			
			FunctionDeclarationFallBackContext fn=(FunctionDeclarationFallBackContext) rlc;
			//temp.nameElement=fn.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_functionDeclarationFallBack;
			return getContractElement(temp, rlc.getParent());
			
		}
		else if(rlc.getRuleIndex()==SolidityParser.RULE_functionDeclarationAnonymous)
		{
			
			FunctionDeclarationAnonymousContext fn=(FunctionDeclarationAnonymousContext) rlc;
			//temp.nameElement=fn.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_functionDeclarationAnonymous;
			return getContractElement(temp, rlc.getParent());
			
		}
		else if(rlc.getRuleIndex()==SolidityParser.RULE_contractElement)
		{
			ContractElementContext contract=(ContractElementContext) rlc;
			temp.nameContract=contract.Identifier().getText();
			return temp;
		}
		else{
			return getContractElement(temp, rlc.getParent());
		}
		
	}
	*/
	private DataContractElement getContractElement(DataContractElement temp,RuleContext rlc){
		
		if(rlc.getRuleIndex()==SolidityParser.RULE_functionDeclaration)
		{
			
			FunctionDeclarationContext functionD=(FunctionDeclarationContext) rlc;
			temp.nameElement=functionD.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_functionDeclaration;
			return getContractElement(temp, rlc.getParent());
			
		}
		else if(rlc.getRuleIndex()==SolidityParser.RULE_modifierDeclaration)
		{
			
			ModifierDeclarationContext modD=(ModifierDeclarationContext) rlc;
			temp.nameElement=modD.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_modifierDeclaration;
			return getContractElement(temp, rlc.getParent());
			
		}
		else if(rlc.getRuleIndex()==SolidityParser.RULE_varGlobalSolidity)
		{
			//--ContractElementContext contract=(ContractElementContext) rlc;
			//--temp.nameContract=contract.Identifier().getText();
			temp.typeElement=SolidityParser.RULE_varGlobalSolidity;
			return getContractElement(temp, rlc.getParent());
		}
		
		else if(rlc.getRuleIndex()==SolidityParser.RULE_contractElement)
		{
			ContractElementContext contract=(ContractElementContext) rlc;
			temp.nameContract=contract.Identifier().getText();
			return temp;
		}
	
		else{
			return getContractElement(temp, rlc.getParent());
		}
		
		
	}
	
}
