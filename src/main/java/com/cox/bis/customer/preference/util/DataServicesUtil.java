package com.cox.bis.customer.preference.util;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.net.www.protocol.http.HttpURLConnection;

@Component("dataServicesUtil")
public class DataServicesUtil {
	
	public Object transform(Exchange exchange, Object body){
		HttpURLConnection ss = exchange.getIn().getBody(HttpURLConnection.class);
		@SuppressWarnings("unused")
		String a = "sds";
		Node env = getDesiredNode(exchange,(Node)body,"Envelope");
		Node bdy = getDesiredNode(exchange,env,"Body");
		Node dper = getDesiredNode(exchange,bdy,"SingleAddressTransactionalOut");
		return dper;
	}
	
	public Object transformForAddressSuggestions(Exchange exchange, Object body){
		Node env = getDesiredNode(exchange,(Node)body,"Envelope");
		Node bdy = getDesiredNode(exchange,env,"Body");
		Node dper = getDesiredNode(exchange,bdy,"DqmUsaAddressSuggestionsOut");
		return dper;
	}
	
	public Object transformFormBatch(Exchange exchange, Object body){
		Node env = getDesiredNode(exchange,(Node)body,"Envelope");
		Node bdy = getDesiredNode(exchange,env,"Body");
		Node dper = getDesiredNode(exchange,bdy,"BatchJobResponse");
		return dper;
	}	
	
	private Node getDesiredNode(Exchange exchange, Node parent,String name){
		NodeList nl = parent.getChildNodes();
		for(int i=0;i<nl.getLength();i++){
			Node n =  nl.item(i);
			String prefix = n.getPrefix();
			if(prefix !=null){
				String ns = n.getNamespaceURI();
				if(ns!=null){
					
				}
			}
			String nname = n.getLocalName();
			if(name.equalsIgnoreCase(nname) || "Fault".equalsIgnoreCase(nname)){
				if("Fault".equalsIgnoreCase(nname)){
					exchange.setProperty("isFault", true);
				}
				return n;
			}
		}
		return null;
	}
}