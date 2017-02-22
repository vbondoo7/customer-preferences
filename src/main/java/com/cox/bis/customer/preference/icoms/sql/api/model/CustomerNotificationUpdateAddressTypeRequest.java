package com.cox.bis.customer.preference.icoms.sql.api.model;
public class CustomerNotificationUpdateAddressTypeRequest extends OldIcomsSqlRequest {

	private static final long serialVersionUID = 1L;

	private String APPNAME;
	private String ENVNAME;
	private Integer SITEID;
	private Integer CUSTNUM;
	private String NOTFMETHOD;
	private String NOTFADDR1;
	private String NOTFADDR2;
	private String SWAPUSERID;

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
		super.setSiteIdForDataSourceAssignment(String.format("%03d", sITEID));
	}

	public Integer getCUSTNUM() {
		return CUSTNUM;
	}

	public void setCUSTNUM(Integer cUSTNUM) {
		CUSTNUM = cUSTNUM;
	}

	public String getNOTFMETHOD() {
		return NOTFMETHOD;
	}

	public void setNOTFMETHOD(String nOTFMETHOD) {
		NOTFMETHOD = nOTFMETHOD;
	}

	public String getNOTFADDR1() {
		return NOTFADDR1;
	}

	public void setNOTFADDR1(String nOTFADDR1) {
		NOTFADDR1 = nOTFADDR1;
	}

	public String getNOTFADDR2() {
		return NOTFADDR2;
	}

	public void setNOTFADDR2(String nOTFADDR2) {
		NOTFADDR2 = nOTFADDR2;
	}

	public String getSWAPUSERID() {
		return SWAPUSERID;
	}

	public void setSWAPUSERID(String sWAPUSERID) {
		SWAPUSERID = sWAPUSERID;
	}

}