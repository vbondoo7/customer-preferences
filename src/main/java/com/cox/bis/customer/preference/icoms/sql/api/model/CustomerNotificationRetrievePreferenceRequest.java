package com.cox.bis.customer.preference.icoms.sql.api.model;

public class CustomerNotificationRetrievePreferenceRequest extends OldIcomsSqlRequest{
	private static final long serialVersionUID = 1L;

	private String APPNAME;
	private String ENVNAME;	
	private Integer SITEID;
	private Integer CUSTNUM;
	private String  SWAPUSERID;
	private Integer NUMBEROFRECORDS;

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String aPPNAME) {
		APPNAME = aPPNAME;
	}

	public String getENVNAME() {
		return ENVNAME;
	}

	public void setENVNAME(String eNVNAME) {
		ENVNAME = eNVNAME;
	}

	/**
	 * @return the sITEID
	 */
	public Integer getSITEID() {
		return SITEID;
	}

	/**
	 * @param sITEID
	 *            the sITEID to set
	 */
	public void setSITEID(Integer sITEID) {
		SITEID = sITEID;
		super.setSiteIdForDataSourceAssignment(String.format("%03d", sITEID));
	}

	/**
	 * @return the cUSTNUM
	 */
	public Integer getCUSTNUM() {
		return CUSTNUM;
	}

	/**
	 * @param cUSTNUM
	 *            the cUSTNUM to set
	 */
	public void setCUSTNUM(Integer cUSTNUM) {
		CUSTNUM = cUSTNUM;
	}

	/**
	 * @return the sWAPUSERID
	 */
	public String getSWAPUSERID() {
		return SWAPUSERID;
	}

	/**
	 * @param sWAPUSERID
	 *            the sWAPUSERID to set
	 */
	public void setSWAPUSERID(String sWAPUSERID) {
		SWAPUSERID = sWAPUSERID;
	}

	/**
	 * @return the nUMBEROFRECORDS
	 */
	public Integer getNUMBEROFRECORDS() {
		return NUMBEROFRECORDS;
	}

	/**
	 * @param nUMBEROFRECORDS
	 *            the nUMBEROFRECORDS to set
	 */
	public void setNUMBEROFRECORDS(Integer nUMBEROFRECORDS) {
		NUMBEROFRECORDS = nUMBEROFRECORDS;
	}
}
