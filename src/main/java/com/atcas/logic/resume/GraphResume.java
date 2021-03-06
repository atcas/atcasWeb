package com.atcas.logic.resume;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.atcas.grammar.antrl.solidity.UnderlineListener;
import com.atcas.grammar.antrl.solidity.base.SolidityLexer;
import com.atcas.grammar.antrl.solidity.base.SolidityParser;
import com.atcas.logic.resume.data.CountElement;
import com.atcas.logic.resume.data.ResumeData;
import com.atcas.models.graphTree.Leaf;
import com.atcas.models.graphTree.Node;



public abstract class GraphResume {
	
	public static List<Node>  getGraph(String data)  {
		
		List<Node> nodeContracts= new ArrayList<Node>();
		try
		{
			// create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(data);
			// create a lexer that feeds off of input CharStream
			SolidityLexer lexer = new SolidityLexer(input);
			// create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// create a parser that feeds off the tokens buffer
			SolidityParser parser = new SolidityParser(tokens);
			//SolidityParserPrinter parser = new SolidityParserPrinter(tokens);
			//PropertyFilePrinter parser= new PropertyFilePrinter(tokens);
			UnderlineListener underlineListener = new UnderlineListener();
			parser.removeErrorListeners(); // remove ConsoleErrorListener
			parser.addErrorListener(underlineListener); // add ours
			
			ParseTree tree = parser.program(); // begin parsing at init rule
			//
			
			SolidityLoader loader= new SolidityLoader();
			ParseTreeWalker walker = new ParseTreeWalker();
	
			walker.walk(loader, tree);
			 
			SolidityLoaderResume loaderResume=new SolidityLoaderResume(loader.getContracts());
			
			walker.walk(loaderResume, tree);
			System.out.println(tree.toStringTree(parser)); // print LISP-style tree
			
			
			
			
			List<ResumeData> lResumeData= loaderResume.getListResumeData();
			for(int i =0;i< lResumeData.size();i++)
			{
				ResumeData rmd= lResumeData.get(i);
				Node nodeCont= new Node();
				nodeCont.setName(rmd.getName());
				nodeCont.setTypeNode(ResumeData.CONTRC_ELEM);
				nodeContracts.add(nodeCont);
				List<String> lFn=rmd.getContract().getFunctionDeclaration();
				for(int j=0;j<lFn.size();j++)
				{
					Node nodeFn= new Node();
					nodeFn.setName(lFn.get(j));
					nodeFn.setTypeNode(ResumeData.FNT_ELEM);
					
					/*
					Integer elementVarLocal=0;
					Integer elementFNLocal=0;
					Integer elementMDLocal=0;
					Integer elementVarExt=0;
					Integer elementFNExt=0;
					Integer elementMDExt=0;
					*/
					List<String> varGlobal= rmd.getListKeyFunctionDeclarationVarGlobal(lFn.get(j));
					
					for(int k=0;varGlobal!=null && k< varGlobal.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(varGlobal.get(k));
						CountElement cntEl=rmd.getValueFunctionDeclarationVarGlobal(lFn.get(j), varGlobal.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setTypeNode(cntEl.getTypeElement());
						lf.setExternData(cntEl.getNameContract());
				
						nodeFn.addChild(lf);
					}
					
					List<String> fnGlobal= rmd.getListKeyFunctionDeclarationFunctionDeclaration (lFn.get(j));
					
					for(int k=0;fnGlobal!=null && k< fnGlobal.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(fnGlobal.get(k));
						CountElement cntEl=rmd.getValueFunctionDeclarationFucntionDeclaration(lFn.get(j), fnGlobal.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setTypeNode(cntEl.getTypeElement());
						lf.setExternData(cntEl.getNameContract());
				
						nodeFn.addChild(lf);
					}
					
					
					
					List<String> eventGlobal= rmd.getListKeyFunctionDeclarationEventGlobal (lFn.get(j));
					
					for(int k=0;eventGlobal!=null && k< eventGlobal.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(eventGlobal.get(k));
						CountElement cntEl=rmd.getValueFunctionDeclarationEventGlobal(lFn.get(j), eventGlobal.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setTypeNode(cntEl.getTypeElement());
						lf.setExternData(cntEl.getNameContract());
				
						nodeFn.addChild(lf);
					}
					
					
					
					List<String> listMdf= rmd.getListKeyFunctionDeclarationModifierDeclaration(lFn.get(j));
					
					for(int k=0;listMdf!=null && k< listMdf.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(listMdf.get(k));
						
						CountElement cntEl=rmd.getValueFunctionDeclarationModifierDeclaration(lFn.get(j), listMdf.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setExternData(cntEl.getNameContract());
						lf.setTypeNode(cntEl.getTypeElement());
						
						//--lf.setCount(rmd.getValueFunctionDeclarationModifierDeclaration(lFn.get(j), listMdf.get(k)).getCount() );
						nodeFn.addChild(lf);
					}
					
					
					
					nodeCont.addChild(nodeFn);
					
				}
				List<String> lMod=rmd.getContract().getModifierDeclaration();
				for(int j=0;j<lMod.size();j++)
				{
					Node nodeMd= new Node();
					nodeMd.setName(lMod.get(j));
					nodeMd.setTypeNode(ResumeData.MODF_ELEM);
					
					
					
					List<String> varGlobal= rmd.getListKeyModifierDeclarationVarGlobal(lMod.get(j));
					
					for(int k=0;varGlobal!=null && k< varGlobal.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(varGlobal.get(k));
						CountElement cntEl=rmd.getValueModifierDeclarationVarGlobal(lMod.get(j), varGlobal.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setExternData(cntEl.getNameContract());
						lf.setTypeNode(cntEl.getTypeElement());
						
						//--lf.setCount(rmd.getValueModifierDeclarationVarGlobal(lMod.get(j), varGlobal.get(k)).getCount() );
						nodeMd.addChild(lf);
					}
					
					
					
					
					List<String> fnGlobal= rmd.getListKeyModifierDeclarationFunctionDeclaration(lMod.get(j));
					
					for(int k=0;fnGlobal!=null && k< fnGlobal.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(fnGlobal.get(k));
						CountElement cntEl=rmd.getValueModifierDeclarationFunctionDeclaration(lMod.get(j), fnGlobal.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setExternData(cntEl.getNameContract());
						lf.setTypeNode(cntEl.getTypeElement());
						
						//--lf.setCount(rmd.getValueModifierDeclarationVarGlobal(lMod.get(j), varGlobal.get(k)).getCount() );
						nodeMd.addChild(lf);
					}
					
					
					List<String> eventGlobal= rmd.getListKeyModifierDeclarationEventGlobal(lMod.get(j));
					
					for(int k=0;fnGlobal!=null && k< fnGlobal.size();k++)
					{
						Leaf lf=new Leaf();
						lf.setName(eventGlobal.get(k));
						CountElement cntEl=rmd.getValueModifierDeclarationEventGlobal (lMod.get(j), eventGlobal.get(k));
						lf.setCount(cntEl.getCount()   );
						lf.setExternData(cntEl.getNameContract());
						lf.setTypeNode(cntEl.getTypeElement());
						
						//--lf.setCount(rmd.getValueModifierDeclarationVarGlobal(lMod.get(j), varGlobal.get(k)).getCount() );
						nodeMd.addChild(lf);
					}
					
					
					
					
					
					
					nodeCont.addChild(nodeMd);
					
				}
		
			}
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return nodeContracts;

		
		
		}

}
