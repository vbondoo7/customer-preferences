package com.cox.bis.customer.preference.icoms.sql.api.model;

public class CustomerNotificationRetrieveUnassignSMSMethodsRequest extends OldIcomsSqlRequest {

	private static final long serialVersionUID = 1L;

	private String APPNAME;
	private String ENVNAME;	
	private Integer SITEID;
	private Integer CUSTNUM;
	private String 	SWAPUSERID;
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

	public Integer getSITEID() {
		return SITEID;
	}

	public void setSITEID(Integer sITEID) {
		SITEID = sITEID;
		super.setSiteIdForDataSourceAssignment(String.format("%03d", SITEID));
	}

	public Integer getCUSTNUM() {
		return CUSTNUM;
	}

	public void setCUSTNUM(Integer cUSTNUM) {
		CUSTNUM = cUSTNUM;
	}

	

	public String getSWAPUSERID() {
		return SWAPUSERID;
	}

	public void setSWAPUSERID(String sWAPUSERID) {
		SWAPUSERID = sWAPUSERID;
	}

	public Integer getNUMBEROFRECORDS() {
		return NUMBEROFRECORDS;
	}

	public void setNUMBEROFRECORDS(Integer nUMBEROFRECORDS) {
		NUMBEROFRECORDS = nUMBEROFRECORDS;
	}
}
