package com.cox.bis.customer.preference.util;

/**
 * @author jahill
 *
 */
public class ValidationHelper {
	public static String translateAddressType(String addressType) {
		String newAddressType = null;
		switch (addressType) {
		case "HP":
			newAddressType = "Phon1";
			break;
		case "BP":
			newAddressType = "Phon2";
			break;
		case "OP":
			newAddressType = "Phon3";
			break;
		case "E":
			newAddressType = "Email";
			break;
		case "P":
			newAddressType = "Phone";
			break;
		case "S":
			newAddressType = "Sms";
			break;
		case "EM":
			newAddressType = "Email";
			break;
		case "PHON1":
			newAddressType = "HP";
			break;
		case "PHON2":
			newAddressType = "BP";
			break;
		case "PHON3":
			newAddressType = "OP";
			break;
		case "EMAIL":
			newAddressType = "EM";
			break;
		}
		return newAddressType;
	}
}
