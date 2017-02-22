/**
 * 
 */
package com.cox.bis.customer.preference.icoms.sql.api.model;

import java.io.Serializable;

/**
 * @author Vinod Singh
 *
 */
public class OldIcomsSqlRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	private String siteIdForDataSourceAssignment;

	/**
	 * @return the siteIdForDataSourceAssignment
	 */
	public String getSiteIdForDataSourceAssignment() {
		return siteIdForDataSourceAssignment;
	}

	/**
	 * @param siteIdForDataSourceAssignment the siteIdForDataSourceAssignment to set
	 */
	public void setSiteIdForDataSourceAssignment(String siteIdForDataSourceAssignment) {
		this.siteIdForDataSourceAssignment = siteIdForDataSourceAssignment;
	}

}