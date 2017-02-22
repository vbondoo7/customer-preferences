package com.cox.bis.customer.preference.util;

import java.util.List;
import java.util.Map;

import com.cox.bis.customer.preference.exception.BusinessException;
import com.cox.bis.customer.preference.exception.InvalidSiteIdException;

public class Util {
	
	/***
	 * Method: isNullEmpty Description: checks if object is Null or Empty
	 * 
	 * @category method
	 * @author jahill
	 * @param object
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Boolean isNullEmpty(Object object) {
		Boolean isNullOrEmpty = Boolean.FALSE;
		if (object == null) isNullOrEmpty = Boolean.TRUE;
		else if (object instanceof String && ((String) object).trim().equals("")) isNullOrEmpty = Boolean.TRUE;
		return isNullOrEmpty;
	}

	/***
	 * Method: isNullEmpty Description: checks if List is Null or Empty
	 * 
	 * @category method
	 * @author jahill
	 * @param object
	 *            Object
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean isNullEmpty(List<?> object) {
		Boolean isNullOrEmpty = Boolean.FALSE;
		if (object == null) isNullOrEmpty = Boolean.TRUE;
		else if (object instanceof List && ((List) object).isEmpty()) isNullOrEmpty = Boolean.TRUE;
		return isNullOrEmpty;
	}

	/***
	 * Method: isNullEmpty Description: checks if Map is Null or Empty
	 * 
	 * @category method
	 * @author jahill
	 * @param object
	 *            Object
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean isNullEmpty(Map<?, ?> object) {
		Boolean isNullOrEmpty = Boolean.FALSE;
		if (object == null) isNullOrEmpty = Boolean.TRUE;
		else if (object instanceof Map && ((Map) object).isEmpty()) isNullOrEmpty = Boolean.TRUE;
		return isNullOrEmpty;
	}
	
	public static Boolean isNotNullEmpty(Object object) {
		Boolean isNotNullOrEmpty = Boolean.TRUE;
		if (object == null) isNotNullOrEmpty = Boolean.FALSE;
		else if (object instanceof String && ((String) object).trim().equals("")) isNotNullOrEmpty = Boolean.FALSE;
		return isNotNullOrEmpty;
	}
	
	@SuppressWarnings("rawtypes")
	public static Boolean isNotNullEmpty(List<?> object) {
		Boolean isNotNullOrEmpty = Boolean.TRUE;
		if (object == null) return isNotNullOrEmpty = Boolean.FALSE;
		else if (object instanceof List && ((List) object).isEmpty()) return isNotNullOrEmpty = Boolean.FALSE;
		return isNotNullOrEmpty;
	}
	
	public static String[] splitText(String text, int maxLength) throws BusinessException {
		text = text.trim();
		if (text == null || text.isEmpty()) {
			throw new BusinessException("SPLIT_TEXT_FAILURE" + "- The 'text' input for Util.splitText is null or empty");
		} else if (text.length() <= maxLength) { return new String[] { text }; }
		// Generate temporary array length
		String[] tempText = new String[(int) Math.round((double) text.length() / (double) maxLength) * 2];
		String[] splitText = null;
		if (!(text == null || text.isEmpty())) {
			int textIndex = 0;
			int i = 0;
			// Loop through string by 40 characters segments
			while (text.length() > 0) {
				// Break line on (1) less than 40 characters long, (2) 40 character segment minus lastIndexOf <SPACE> to keep words together, (3) 40 character segment if no <SPACE> in last 15
				// characters *break long words over 15 characters
				textIndex = (text.length() > maxLength) ? (text.substring(maxLength - 15, maxLength).contains(" ")) ? text.substring(0, maxLength).lastIndexOf(" ") + 1 : maxLength : text.length();
				tempText[i] = text.substring(0, textIndex);
				text = text.substring(textIndex, text.length());
				i++;
			}
			// New splitText[] Array for Return, *exact Array length based on iteration count and transfer elements from tempText[]
			splitText = new String[i];
			for (int j = 0; j < i; j++)
				splitText[j] = tempText[j];
		}
		return splitText;
	}
	
	public static String mapSiteIdToSiteCode(String siteId) throws InvalidSiteIdException {
		if (siteId == null) throw new InvalidSiteIdException("null");
		// Map Site Code to Properties
		String siteCode;
		switch (siteId) {
		case "1":
			siteCode = "MAC";
			break;
		case "01":
			siteCode = "MAC";
			break;
		case "001":
			siteCode = "MAC";
			break;
		case "126":
			siteCode = "LOU";
			break;
		case "131":
			siteCode = "OKC";
			break;
		case "132":
			siteCode = "OMA";
			break;
		case "135":
			siteCode = "PEN";
			break;
		case "182":
			siteCode = "BTR";
			break;
		case "186":
			siteCode = "TUL";
			break;
		case "203":
			siteCode = "BAK";
			break;
		case "214":
			siteCode = "GAN";
			break;
		case "215":
			siteCode = "HRD";
			break;
		case "216":
			siteCode = "CON";
			break;
		case "238":
			siteCode = "RHI";
			break;
		case "239":
			siteCode = "ROA";
			break;
		case "333":
			siteCode = "ORG";
			break;
		case "334":
			siteCode = "PAL";
			break;
		case "342":
			siteCode = "SAB";
			break;
		case "436":
			siteCode = "PHX";
			break;
		case "476":
			siteCode = "LAS";
			break;
		case "477":
			siteCode = "NVA";
			break;
		case "541":
			siteCode = "SAN";
			break;
		case "580":
			siteCode = "KAN";
			break;
		case "609":
			siteCode = "CLE";
			break;
		case "7":
			siteCode = "OCE";
			break;
		case "07":
			siteCode = "OCE";
			break;
		case "007":
			siteCode = "OCE";
			break;
		case "106":
			siteCode = "ARK";
			break;
		case "150":
			siteCode = "WTX";
			break;
		case "308":
			siteCode = "ALE";
			break;
		case "317":
			siteCode = "EUR";
			break;
		case "581":
			siteCode = "NCA";
			break;
		case "582":
			siteCode = "GOK";
			break;
		case "707":
			siteCode = "ENG";
			break;
		case "811":
			siteCode = "ETX";
			break;
		case "888":
			siteCode = "WIR";
			break;
		case "1001":
			siteCode = "SE";
			break;
		case "1126":
			siteCode = "LA";
			break;
		case "1131":
			siteCode = "OK";
			break;
		case "1216":
			siteCode = "FL";
			break;
		case "1135":
			siteCode = "NE";
			break;
		case "1436":
			siteCode = "SW";
			break;
		case "1477":
			siteCode = "VA";
			break;
		case "1541":
			siteCode = "CA";
			break;
		case "1580":
			siteCode = "CEN";
			break;
		case "0111":
			siteCode = "GGGG";
			break;
		case "011":
			siteCode = "OKC";
			break;
		case "0333":
			siteCode = "TST";
			break;
		default:
			siteCode = null;
			break;
		}
		if (siteCode == null) throw new InvalidSiteIdException(siteId);
		return siteCode;
	}

}
