package com.atcas.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atcas.logic.resume.GraphResume;
import com.atcas.models.graphTree.Node;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RestController
public class AnalizarRestController {
	
	private final Logger logger = LoggerFactory.getLogger(AnalizarRestController.class);

	
	

	

	  
	  
	 @RequestMapping(value ="/analizar"
			 , method = RequestMethod.POST
			 , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
			 , produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<Node>> analizar(@ModelAttribute("codeSolidity") String codeSolidity,HttpEntity<String> httpEntity) {//REST Endpoint.
	 
//		 System.out.println("mantes de entrar");
//		 
	  		String json = httpEntity.getBody();
//	  		System.out.println(json);
//	  		System.out.println(codeSolidity);
	        return new ResponseEntity<List<Node>>(GraphResume.getGraph(codeSolidity), HttpStatus.OK);
	 }
	  
	
}