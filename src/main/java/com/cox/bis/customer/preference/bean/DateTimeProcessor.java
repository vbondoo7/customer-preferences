package com.cox.bis.customer.preference.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cox.bis.customer.preference.exception.InvalidDateFormatException;

public class DateTimeProcessor {
	/***
	 * toCenturyDate
	 * Assumes date string is in "yyyyMMdd" format.
	 * Output = "cMMdd"
	 * @param date
	 * @return icomsDate
	 * @throws InvalidDateFormatException 
	 * @throws Exception
	 */
	public static String toCenturyDate(String date) throws InvalidDateFormatException {
		String icomsDate = toCenturyDate(date, "yyyyMMdd");
		return icomsDate;
	}

	/***
	 * toCenturyDate
	 * Date string is in format of String dateFormat (e.g. - "MM/dd/yyyy").
	 * Output = "cMMdd"
	 * @param date
	 * @param dateFormat
	 * @return icomsDate
	 * @throws Exception
	 */
	public static String toCenturyDate(Date date) {
        // Creating SimpleDateFormat with yyyyMMdd format e.g."20110914"
        DateFormat yyyyMMddDateFormatter = new SimpleDateFormat("yyyyMMdd");
        yyyyMMddDateFormatter.setLenient(false);
        String stringDate = yyyyMMddDateFormatter.format(date);
		String icomsDate = Integer.toString(Integer.parseInt(stringDate.substring(0, 2)) + 1).substring(1, 2) + stringDate.substring(2);
		return icomsDate;
	}

	/***
	 * toCenturyDate
	 * Date string is in format of String dateFormat (e.g. - "MM/dd/yyyy=09/14/2011").
	 * Output = "cMMdd"
	 * @param date
	 * @param dateFormat
	 * @return icomsDate
	 * @throws InvalidDateFormatException 
	 * @throws Exception
	 */
	public static String toCenturyDate(String date, String dateFormat) throws InvalidDateFormatException{
        // Creating SimpleDateFormat with String dateFormat e.g."MM/dd/yyyy=09/14/2011"
        DateFormat yyyyMMddDateFormatter = new SimpleDateFormat("yyyyMMdd");
        DateFormat customDateFormatter = new SimpleDateFormat(dateFormat);
        customDateFormatter.setLenient(false);
        Date javaDate;
        try {
			javaDate = customDateFormatter.parse(date.trim());
		} catch (ParseException pe) {
			throw new InvalidDateFormatException(pe);
		}
        date = yyyyMMddDateFormatter.format(javaDate);
		String icomsDate = Integer.toString(Integer.parseInt(date.trim().substring(0, 2)) + 1).substring(1, 2) + date.trim().substring(2);
		return icomsDate;
	}
	
	/***
	 * fromCenturyDate
	 * Input = Assumes date string is in "cMMdd" format.
	 * Output = "yyyyMMdd"
	 * @param icomsDate
	 * @return date
	 * @throws InvalidDateFormatException 
	 * @throws ParseException 
	 */
	public static String fromCenturyDate(String icomsDate) throws InvalidDateFormatException {
		String date = fromCenturyDate(icomsDate, "yyyyMMdd");
		return date;
	}

	/***
	 * fromCenturyDate
	 * Date string is in format of String dateFormat (e.g. - "MM/dd/yyyy=09/14/2011").
	 * Input = "cMMdd"
	 * Output = date string is in format of String dateFormat (e.g. - "MM/dd/yyyy=09/14/2011").
	 * @param date
	 * @param dateFormat
	 * @return icomsDate
	 * @throws InvalidDateFormatException 
	 */
	public static String fromCenturyDate(String icomsDate, String dateFormat) throws InvalidDateFormatException {
		icomsDate = String.format("%07d", Integer.parseInt(icomsDate));
        DateFormat yyyyMMddDateFormatter = new SimpleDateFormat("yyyyMMdd");        
        DateFormat customDateFormatter = new SimpleDateFormat(dateFormat);
        customDateFormatter.setLenient(false);
		String date = Integer.toString(Integer.parseInt(icomsDate.substring(0, 1)) + 19) + icomsDate.substring(1);
        Date javaDate;
		try {
			javaDate = yyyyMMddDateFormatter.parse(date.trim());
		} catch (ParseException e) {
			throw new InvalidDateFormatException(e);
		}
            date = customDateFormatter.format(javaDate);
		return date;
	}

	public static String toyyyyMMddDate(Date date) {
        DateFormat yyyyMMddDateFormatter = new SimpleDateFormat("yyyyMMdd");
        yyyyMMddDateFormatter.setLenient(false);
		String dateString = yyyyMMddDateFormatter.format(date);
		return dateString;
	}

	public static String toyyyyMMddDate(String date, String dateFormat) throws InvalidDateFormatException {
        DateFormat yyyyMMddDateFormatter = new SimpleDateFormat("yyyyMMdd");
        DateFormat customDateFormatter = new SimpleDateFormat(dateFormat);
        customDateFormatter.setLenient(false);
        Date javaDate;
		try {
			javaDate = customDateFormatter.parse(date.trim());
		} catch (ParseException e) {
			throw new InvalidDateFormatException(e);
		}
		date = yyyyMMddDateFormatter.format(javaDate); 
		return date;
	}

	public static Date fromyyyyMMddDate(String date) throws InvalidDateFormatException {
        DateFormat yyyyMMddDateFormatter = new SimpleDateFormat("yyyyMMdd");
        yyyyMMddDateFormatter.setLenient(false);
        Date javaDate;
		try {
			javaDate = yyyyMMddDateFormatter.parse(date.trim());
		} catch (ParseException e) {
			throw new InvalidDateFormatException(e);
		}
		return javaDate;
	}
	
	public static Date fromyyyyMMddHHmmssDateTime(String dateTime) throws InvalidDateFormatException {
        DateFormat yyyyMMddHHmmssDateTimeFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        yyyyMMddHHmmssDateTimeFormatter.setLenient(false);
        Date javaDate;
		try {
			javaDate = yyyyMMddHHmmssDateTimeFormatter.parse(dateTime.trim());
		} catch (ParseException e) {
			throw new InvalidDateFormatException(e);
		}
		return javaDate;
	}

	public static String fromyyyyMMddHHmmssDateTime(String dateTime, String dateTimeFormat) throws InvalidDateFormatException {
        DateFormat yyyyMMddHHmmssDateTimeFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        DateFormat customDateFormatter = new SimpleDateFormat(dateTimeFormat);
        customDateFormatter.setLenient(false);
        Date javaDate;
		try {
			javaDate = yyyyMMddHHmmssDateTimeFormatter.parse(dateTime.trim());
		} catch (ParseException e) {
			throw new InvalidDateFormatException(e);
		}
        dateTime = customDateFormatter.format(javaDate); 
		return dateTime;
	}
}