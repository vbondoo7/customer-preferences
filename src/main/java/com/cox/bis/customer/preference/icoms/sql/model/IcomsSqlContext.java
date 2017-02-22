package com.cox.bis.customer.preference.icoms.sql.model;
/***
 * 
 * @author Gajanand
 *
 */

public class IcomsSqlContext {
	private String transactionId;
	private String clientTransactionId;
	private String sessionId;
	private String clientSessionId;
	private String clientId;
	
	public IcomsSqlContext(String transactionId, 
			String clientTransactionId, String sessionId, 
			String clientSessionId, String clientId) {
		this.transactionId = transactionId;
		this.clientTransactionId = clientTransactionId;
		this.sessionId = sessionId;
		this.clientSessionId = clientSessionId;
		this.clientId = clientId;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getClientTransactionId() {
		return clientTransactionId;
	}
	public void setClientTransactionId(String clientTransactionId) {
		this.clientTransactionId = clientTransactionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getClientSessionId() {
		return clientSessionId;
	}
	public void setClientSessionId(String clientSessionId) {
		this.clientSessionId = clientSessionId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}