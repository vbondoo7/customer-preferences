package com.cox.bis.customer.preference.icoms.sql.api.model;
/**
 * @author Kumar Saurav
 *
 * 
 */
public class CustomerNotificationUpdateMethodRequest extends OldIcomsSqlRequest {

	private static final long serialVersionUID = 1L;
	private String APPNAME;
	private String ENVNAME;
	private Integer SITEID;
	private Integer CUSTNUM;
	private String NOTFMETHOD;
	private String NOTFADDR;
	private String ADDRESS;
	private String MOBILE;
	private String PRIMARY;
	private String NOTFOPTN;
	private String VERIFYSTS;
	private String CONFIRMSTS;
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
		super.setSiteIdForDataSourceAssignment(String.format("%03d", SITEID));
	}

	public String getNOTFMETHOD() {
		return NOTFMETHOD;
	}

	public void setNOTFMETHOD(String nOTFMETHOD) {
		NOTFMETHOD = nOTFMETHOD;
	}

	public String getNOTFADDR() {
		return NOTFADDR;
	}

	public void setNOTFADDR(String nOTFADDR) {
		NOTFADDR = nOTFADDR;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getPRIMARY() {
		return PRIMARY;
	}

	public void setPRIMARY(String pRIMARY) {
		PRIMARY = pRIMARY;
	}

	public String getNOTFOPTN() {
		return NOTFOPTN;
	}

	public void setNOTFOPTN(String nOTFOPTN) {
		NOTFOPTN = nOTFOPTN;
	}

	public String getVERIFYSTS() {
		return VERIFYSTS;
	}

	public void setVERIFYSTS(String vERIFYSTS) {
		VERIFYSTS = vERIFYSTS;
	}

	public Integer getCUSTNUM() {
		return CUSTNUM;
	}

	public void setCUSTNUM(Integer cUSTNUM) {
		CUSTNUM = cUSTNUM;
	}

	public String getCONFIRMSTS() {
		return CONFIRMSTS;
	}

	public void setCONFIRMSTS(String cONFIRMSTS) {
		CONFIRMSTS = cONFIRMSTS;
	}

	public String getSWAPUSERID() {
		return SWAPUSERID;
	}

	public void setSWAPUSERID(String sWAPUSERID) {
		SWAPUSERID = sWAPUSERID;
	}

}