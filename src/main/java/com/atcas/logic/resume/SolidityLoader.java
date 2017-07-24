package com.atcas.logic.resume;



import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import com.atcas.grammar.antrl.solidity.base.SolidityBaseListener;
import com.atcas.grammar.antrl.solidity.base.SolidityParser;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.ContractElementContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.ContractExtendElementConstructorContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.EnumGlobalSolidityContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.EventDeclarationContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.FunctionDeclarationAnonymousContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.FunctionDeclarationContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.FunctionDeclarationFallBackContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.FunctionDeclarationInterfaceContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.IdentifierExpressionContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.MappingGlobalSolidityContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.ModifierDeclarationContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.StructGlobalSolidityContext;
import com.atcas.grammar.antrl.solidity.base.SolidityParser.VarSolidityDeclarationContext;
import com.atcas.logic.resume.data.Contract;


public class SolidityLoader extends SolidityBaseListener {

	Map<String, Contract> contracts;
	
	public SolidityLoader() {
		// TODO Auto-generated constructor stub
		this.contracts= new HashMap<String, Contract>();
		
	}
	
	/*
	public List<Contract> getListContract(){
		return new ArrayList<Contract>(this.contracts.values());
	}
	*/
	public Map<String, Contract> getContracts() {
		return contracts;
	}
	
	
	@Override
	public void enterContractElement(ContractElementContext ctx) {
		// TODO Auto-generated method stub
		//super.enterContractElement(ctx);
		if( !this.contracts.containsKey(ctx.Identifier().getText() ))
		{
			Contract ctc= new Contract(ctx.Identifier().getText());
			if(ctx.contractExtendElement()!=null)
			{
				int tl=ctx.contractExtendElement().getChildCount();
				for(int i =0;i< tl;i++)
				{
					ParseTree ptree=ctx.contractExtendElement().getChild(i);
				
					if(ptree.getPayload().getClass()==ContractExtendElementConstructorContext.class)
					{
						ContractExtendElementConstructorContext cctConstructor=(ContractExtendElementConstructorContext) ptree.getPayload();
						
						ctc.getExtendsContract().add(
								cctConstructor.Identifier().getText()
								);
						
						//System.out.println("pasda");
					}
				}
			}
			this.contracts.put(ctx.Identifier().getText(), ctc);
		}
		
	}

	/*
	private String getName( ParseTree children)
	{
		if(children.getChildCount()>0)
		{
			RuleContext rlCon=(RuleContext) children.getPayload();
			
			if(rlCon.getRuleIndex()==SolidityParser.RULE_singletypeSolidity)
			{
				if(rlCon.getChildCount()>1)
				{
					
					return rlCon.getChild(1).getText();
				}
				else
				{
					return null;
				}
			}else
			{
				return getName(children.getChild(0));
			}
			
		}
		else
		{

			return null;
		}
	}

	*/
	private String getParentContract(RuleContext parent)
	{
		if(parent.getRuleIndex()==SolidityParser.RULE_contractElement)
		{
			ContractElementContext contract=(ContractElementContext) parent;
			return contract.Identifier().getText();
		}
		else
		{
			return getParentContract(parent.getParent());
		}
	}
	
	
	/*
	private String ordenPorNivel( List<ParseTree> children)
	{
		
		List<ParseTree> queue=children;
		while(queue.size()>0)
		{
			
			
			ParseTree nodo=queue.remove(0);
			if(nodo.getChildCount()>0)
			{
				
				for(int i =0;i< nodo.getChildCount();i++)
				{
					queue.add(nodo.getChild(i));
				}
				
				
			}
			else
			{
				
		
					//Token t=(Token) nodo;
					if(nodo.getText().equals("="))
					{
						return nodo.getParent().getChild(0).getText();
					}
				
			
				
			}
	
			
		}
		return null;
	  
		
	}
*/
    @Override
    public void enterIdentifierExpression(IdentifierExpressionContext ctx) {
    	// TODO Auto-generated method stub
    	//super.enterIdentifierExpression(ctx);
    	//System.out.println(ctx.getText());
    	
    	
    	
    }     
     
     
    @Override
    public void enterMappingGlobalSolidity(MappingGlobalSolidityContext ctx) {
    	// TODO Auto-generated method stub
    	//super.enterMappingGlobalSolidity(ctx);
      	 String nameVar=ctx.Identifier().getText();
    	 
     	// if(nameVar!=null){
 			 String contract =getParentContract(ctx.getParent());
 			 this.contracts.get(contract).getVarGlobal().add(nameVar);
 			 //System.out.println("dsaa:" +nameVar+ " "+ contract);
 		// }
    }
              
         
     @Override
     public void enterVarSolidityDeclaration(VarSolidityDeclarationContext ctx) {
    	// TODO Auto-generated method stub
    	
    	 String nameVar=ctx.getChild(ctx.getChildCount()-1).getText();
    	 
    	// if(nameVar!=null){
			 String contract =getParentContract(ctx.getParent());
			 this.contracts.get(contract).getVarGlobal().add(nameVar);
			 //System.out.println("dsaa:" +nameVar+ " "+ contract);
		// }
     }
     
     /*
	 @Override
	public void enterVarGlobalSolidity(VarGlobalSolidityContext ctx) {
		// TODO Auto-generated method stub
		 System.out.println(ctx.getText());
		 String nameVar=ordenPorNivel(ctx.children);
		 if(nameVar!=null){
			 String contract =getParentContract(ctx.getParent());
			 this.contracts.get(contract).getVarGlobal().add(nameVar);
			 //System.out.println("dsaa:" +nameVar+ " "+ contract);
		 }

		 
	}
	 */
	 @Override
	public void enterStructGlobalSolidity(StructGlobalSolidityContext ctx) {
		// TODO Auto-generated method stub
		//super.enterStructGlobalSolidity(ctx);
		String contract =getParentContract(ctx.getParent());
		String name=ctx.Identifier().getText();
		this.contracts.get(contract).getStructGlobal().add(name);
	}
	 
	 
	 @Override
	public void enterEnumGlobalSolidity(EnumGlobalSolidityContext ctx) {
		// TODO Auto-generated method stub
			String contract =getParentContract(ctx.getParent());
			String name=ctx.Identifier().getText();
			this.contracts.get(contract).getEnunGlobal().add(name);
	}
	 @Override
	public void enterModifierDeclaration(ModifierDeclarationContext ctx) {
		// TODO Auto-generated method stub
		//super.enterModifierDeclaration(ctx);
			String contract =getParentContract(ctx.getParent());
			String name=ctx.Identifier().getText();
			this.contracts.get(contract).getModifierDeclaration().add(name);
		 
	}
	 
	@Override
	public void enterEventDeclaration(EventDeclarationContext ctx) {
		// TODO Auto-generated method stub
		String contract =getParentContract(ctx.getParent());
		String name=ctx.Identifier().getText();
		this.contracts.get(contract).getEventGlobal().add(name);
	}
	@Override
	public void enterFunctionDeclaration(FunctionDeclarationContext ctx) {
		// TODO Auto-generated method stub
		//super.enterFunctionDeclaration(ctx);
		String contract =getParentContract(ctx.getParent());
		String name=ctx.Identifier().getText();
		this.contracts.get(contract).getFunctionDeclaration().add(name);
	}
	@Override
	public void enterFunctionDeclarationFallBack(FunctionDeclarationFallBackContext ctx) {
		// TODO Auto-generated method stub
		//super.enterFunctionDeclarationFallBack(ctx);
		
		String contract =getParentContract(ctx.getParent());
		
		this.contracts.get(contract).setHasFallback(true);
		
	}
	@Override
	public void enterFunctionDeclarationInterface(FunctionDeclarationInterfaceContext ctx) {
		// TODO Auto-generated method stub
		//super.enterFunctionDeclarationInterface(ctx);
		
		String contract =getParentContract(ctx.getParent());
		String name=ctx.Identifier().getText();
		this.contracts.get(contract).getFunctionInterface().add(name);
	}
	 
	@Override
	public void enterFunctionDeclarationAnonymous(FunctionDeclarationAnonymousContext ctx) {
		// TODO Auto-generated method stub
		//super.enterFunctionDeclarationAnonymous(ctx);
	}

		
	

}
