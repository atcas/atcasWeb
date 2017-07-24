package com.atcas.logic.resume;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.apache.commons.io.IOUtils;


import com.atcas.grammar.antrl.solidity.UnderlineListener;
import com.atcas.grammar.antrl.solidity.base.SolidityLexer;
import com.atcas.grammar.antrl.solidity.base.SolidityParser;
import com.atcas.logic.resume.data.CountElement;
import com.atcas.logic.resume.data.ResumeData;
import com.atcas.models.graphTree.Leaf;
import com.atcas.models.graphTree.Node;



public class Example2 {
	
	  public static  String getFile(String fileName){
		  String result = "";

		  ClassLoader classLoader = Example2.class.getClassLoader();
		  try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		  } catch (IOException e) {
			e.printStackTrace();
		  }

		  return result;

	  }
	  

		public static void main(String[] args) throws Exception {
			// create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(Example2.getFile("example.sol"));
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
			
			
			
			List<Node> nodeContracts= new ArrayList<Node>();

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
			
			
			
			
			for(int i=0;i< nodeContracts.size();i++)
			{
				
				System.out.println( nodeContracts.get(i).toString() );
			}
			
			
		     //show AST in GUI
	        JFrame frame = new JFrame("Antlr AST");
	        JPanel panel = new JPanel();
	        TreeViewer viewr = new TreeViewer(Arrays.asList(
	                parser.getRuleNames()),tree);
	        viewr.setScale(0.8);//scale a little
	        panel.add(viewr);
	        
	        JScrollPane scrollPane = new JScrollPane(panel);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        
	        frame.add(scrollPane);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(700,700);
	        frame.setVisible(true);
	        
			
			
			}
		
		

}
