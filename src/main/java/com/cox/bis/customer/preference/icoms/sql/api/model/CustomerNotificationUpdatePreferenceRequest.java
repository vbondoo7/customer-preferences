package com.cox.bis.customer.preference.icoms.sql.api.model;
/**
 * @author Kumar Saurav
 *
 * 
 */
public class CustomerNotificationUpdatePreferenceRequest extends OldIcomsSqlRequest {

	private static final long serialVersionUID = 1L;
	private String APPNAME;
	private String ENVNAME;
	private Integer SITEID;
	private Integer CUSTNUM;
	private String NOTFCATG;
	private String NOTFMETH;
	private String NOTFOPTN;
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

	public String getNOTFCATG() {
		return NOTFCATG;
	}

	public void setNOTFCATG(String nOTFCATG) {
		NOTFCATG = nOTFCATG;
	}

	public String getNOTFMETH() {
		return NOTFMETH;
	}

	public void setNOTFMETH(String nOTFMETH) {
		NOTFMETH = nOTFMETH;
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

	public String getNOTFOPTN() {
		return NOTFOPTN;
	}

	public void setNOTFOPTN(String nOTFOPTN) {
		NOTFOPTN = nOTFOPTN;
	}

}