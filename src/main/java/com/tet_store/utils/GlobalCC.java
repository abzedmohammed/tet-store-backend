
package com.javasoft.house_inn.utils;

import java.io.*;

import java.net.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;

public class GlobalCC {

	public static final String X_METER_READING = "/opt/images/OTP_IMAGES/X_METER_READING/";
	public static final String METER_READING = "/opt/images/OTP_IMAGES/METER_READING/";
	public static final String TEMP = "/opt/images/OTP_IMAGES/TMP/";
	public static final String DISCONNECTIONS = "/opt/images/OTP_IMAGES/DISCONNECTIONS/";
	public static final String ACCOUNT_OPENING = "/opt/images/OTP_IMAGES/ACCOUNT_OPENING/";
	public static final String FUND_MY_WATER = "/opt/images/OTP_IMAGES/FUND_MY_WATER/";

	public static boolean INIT_FIREBASE = true;

	public static String URL_FRONT_OFFICE = "";
	public static String UPDATED = "UPDATED.";
	public static String CREATED = "Created.";
	public static String ERROR = "ERROR.";

	public static String RECORD_UPDATED = "Record SAVED Successfully";

	// public static Period period = null;

	public static String getJson(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader;
		try {
			reader = request.getReader();

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String xternString(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader;
		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("---------------------------"+sb.toString());
		return sb.toString();
	}

	public static String getClientIpAddress(HttpServletRequest request) {

		String appUrl = request.getScheme() + "://" + request.getLocalAddr();
		return appUrl;
	}

	public static String getJsonObject(Object object) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	public static String getSysIpAddress() {

		InetAddress ip;
		String hostname;
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();

			System.out.println("Your current IP address : " + ip.getHostAddress() + "...........");
			System.out.println("Your current Hostname : " + hostname.toLowerCase() + "...........");

			return /* ip.getHostAddress(); */ hostname.toLowerCase() + ":8080";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return "localhost";
	}

	public String iso8601Format(Date date) {
		TimeZone tz = TimeZone.getTimeZone("EAT");
		/* "2018-09-05T16:20:00-05:00" */
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(date);
		return nowAsISO;
	}

	public static String getCurrentTime() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

		java.util.Date now = new java.util.Date();

		String strTime = sdfTime.format(now);

		System.out.println("Time: " + strTime);
		return strTime;
	}

	public static boolean isInternetReachable() {
		try {
			URL url = new URL("http://www.google.com");

			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

			Object Data = urlConnect.getContent();
		} catch (UnknownHostException e) {
			Object objData;
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static java.util.Date convertDate(String indate) {
		java.util.Date utilToday = new java.util.Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
		long retDate = 0L;
		try {
			utilToday = sdf1.parse(indate);
			retDate = utilToday.getTime();
			utilToday = new java.util.Date(retDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return utilToday;
	}

	public static java.sql.Date parseSQLDate(String someDate) {
		java.util.Date utilToday = new java.util.Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		java.sql.Date sqlDate = null;
		try {
			utilToday = sdf1.parse(someDate);
			long t = utilToday.getTime();
			sqlDate = new java.sql.Date(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	public static java.sql.Date updateSQLDate(String someDate) {
		java.util.Date utilToday = new java.util.Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		java.sql.Date sqlDate = null;
		try {
			utilToday = sdf1.parse(someDate);
			long t = utilToday.getTime();
			sqlDate = new java.sql.Date(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	public static String CheckNullValues(Object obj) {
		if (obj != null) {
			String myString = obj.toString();
			if (myString.trim().length() < 1) {
				return null;
			}

			if (myString.equalsIgnoreCase("null")) {
				return null;
			}
			return myString;
		}
		return null;
	}

	public static java.util.Date formatLongDateString(String dateString) {
		java.util.Date convertedDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	public static boolean isInternetReachable(String urlString)
	/* 35: */ {
		/* 36: */try
		/* 37: */ {
			/* 38: 49 */URL url = new URL(urlString);
			/* 39: */
			/* 40: */
			/* 41: 52 */HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
			/* 42: */
			/* 43: */
			/* 44: */
			/* 45: 56 */
			return true;
			/* 46: */}
		/* 47: */catch (UnknownHostException e)
		/* 48: */ {
			/* 49: */Object objData;
			/* 50: 60 */e.printStackTrace();
			/* 51: 61 */return false;
			/* 52: */}
		/* 269: */catch (MalformedURLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	public  static String   messageTemplate(String title,String addressee, String message, String url){
		String TEMPLATE = "<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"    <title>FUND_MY_WATER</title>" +
				"    <meta charset=\"utf-8\">" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
				"</head>" +
				"<body style=\"font-family: Arial, sans-serif;\">" +
				"" +
				"<div style=\"max-width: 600px; margin: 0 auto; background-color: #fff; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); border-radius: 4px; padding: 24px;\">" +
				"" +
				"    <h1 style=\"color: #333; text-align: center;\">"+title+"</h1>" +
				"" +
				"    <p style=\"color: #555; font-size: 16px; line-height: 1.5; text-align: center; margin: 24px 0;\">Dear "+addressee+",</p>" +
				"    <p style=\"color: #555; font-size: 16px; line-height: 1.5;text-align: center;\">"+message+"." +
				"" +
				"" +
				"    <p style=\"color: #555; font-size: 16px; line-height: 1.5;text-align: center;\">To view, simply click on the button below:</p>" +
				"" +
				"    <div style=\"text-align: center; margin: 24px 0;\">" +
				"        <a href=\""+url+"\" style=\"display: inline-block; background-color: #00BABA; color: #fff; font-size: 16px; padding: 12px 24px; text-decoration: none; border-radius: 4px;\">VIEW</a>" +
				"    </div>" +
				"    <p style=\"color: #555; font-size: 16px; line-height: 1.5; text-align: right; margin-top: 48px;\">Best regards,<br>Admin</p>" +
				"" +
				"    <div style=\"background-color: #0069DF; color: #fff; font-size: 14px; text-align: center; padding: 28px; margin-top: 48px;margin-left:-4%;margin-right:-4%;\">" +
				"        This email was sent to "  +addressee+ " by FundMyWater. To unsubscribe from future emails, <a href="+url+" style=\"color: #fff; text-decoration: underline;\">click here</a>." +
				"    </div>" +
				"" +
				"</div>" +
				"" +
				"</body>" +
				"</html>";
		return TEMPLATE;
	}

	public static String removeSpecialCharacters(String inputString) {
		String Str1 = null;
		String Str2 = null;
		String Str3 = null;
		String Str4 = null;
		String str5 = null;
		String str6 = null;
		String str7 = null;
		String str8 = null;
		String str9 = null;
		String str10 = null;
		String str11 = null;
		String str12 = null;
		String str13 = null;
		String str14 = null;
		String str15 = null;

		try {
			System.out.println(inputString);

			Str1 = inputString.replaceAll("'", "_");
			System.out.println(Str1);

			Str2 = Str1.replace("(", "_");
			System.out.println(Str2);
			Str3 = Str2.replace(")", "_");
			System.out.println(Str3);
			Str4 = Str3.replace("/", "_");
			str5 = Str4.replace("\\", "_");
			str6 = str5.replace("[", "_");

			str7 = str6.replace("]", "_");
			str8 = str7.replace("{", "_");
			str9 = str8.replace("}", "_");
			str10 = str9.replace(".", "_");
			str11 = str10.replace("+", "PLUS");
			str12 = str11.replace(",", "_");
			str13 = str12.replace(":", "_");
			str14 = str13.replace("%", "_percent_");
			str15 = str14.replace("&", "_");
			// str6=inputString.replaceAll("([\\\\\\.\\[\\{\\(\\*\\+\\?\\^\\$\\|])",
			// "$1");
			System.out.println("str6>>>" + str15);
			return str15;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return str15;
	}

	public static boolean convertImage(String imageDataString, String filename) {

		File mReadfile = new File(METER_READING);
		File mDiscfile = new File(DISCONNECTIONS);

		if (!mReadfile.exists()) {
			if (mReadfile.mkdirs()) {
				System.out.println(METER_READING + ".........DIR CREAED");
			}
		}

		if (!mDiscfile.exists()) {
			if (mDiscfile.mkdirs()) {
				System.out.println(DISCONNECTIONS + ".........DIR CREAED");
			}
		}

		boolean isImageSaved = true;

		String mMessage = "Image Successfully Saved!";

		try {
			/*
			 * Reading a Image file from file system
			 */
			// FileInputStream imageInFile = new FileInputStream(file);
			// byte imageData[] = new byte[(int)file.length()];
			// imageInFile.read(imageData);
			//
			// /*
			// * Converting Image byte array into Base64 String
			// */
			// String imageDataString = encodeImage(imageData);
			//
			// /*
			// * Converting a Base64 String into Image byte array
			// */
			byte[] imageByteArray = decodeImage(imageDataString);

			/*
			 * Write a image byte array into file system
			 */
			FileOutputStream imageOutFile = new FileOutputStream(filename);
			imageOutFile.write(imageByteArray);

			// imageInFile.close();
			imageOutFile.close();

			System.out.println(mMessage);
		} catch (FileNotFoundException e) {
			isImageSaved = false;
			System.out.println("Image not found" + e);
			mMessage = e.getMessage();
			return isImageSaved;
		} catch (IOException ioe) {
			isImageSaved = false;
			mMessage = ioe.getMessage();
			System.out.println("Exception while reading the Image " + ioe);
			return isImageSaved;
		} catch (NullPointerException ex) {
			isImageSaved = false;
			mMessage = ex.getMessage();
			System.out.println("Exception while reading the Image " + ex);
			return isImageSaved;
		}

		return isImageSaved;
	}

	public static Date dateIncrementer(Date v_date, int v_incrementer) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(v_date);
			cal.add(Calendar.DATE, v_incrementer);
			v_date = cal.getTime();
			return v_date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isNumericRegex(String str) {
		if (str == null)
			return false;
		return str.matches("-?\\d+");
	}

	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

	/**
	 * Verifies file's SHA1 checksum
	 * 
	 * @param Filepath     and name of a file that is to be verified
	 * @param testChecksum the expected checksum
	 * @return true if the expeceted SHA1 checksum matches the file's SHA1 checksum;
	 *         false otherwise.
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static boolean verifyChecksum(String file, String testChecksum)
			throws NoSuchAlgorithmException, IOException {
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		FileInputStream fis = new FileInputStream(file);

		byte[] data = new byte[1024];
		int read = 0;
		while ((read = fis.read(data)) != -1) {
			sha1.update(data, 0, read);
		}
		byte[] hashBytes = sha1.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hashBytes.length; i++) {
			sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		String fileHash = sb.toString();

		return fileHash.equals(testChecksum);
	}

	public static int SendSMS(String MSISDN, String Content, String SourceReference, String CampaignId) throws UnsupportedEncodingException {
		String RequestURL = "http://http1.uk.oxygen8.com:8084/fst";

		StringBuffer Response = new StringBuffer("This is a Hello World string. Hello!");

		String Data = ("Channel=" + URLEncoder.encode("KENYA.SAFARICOM", "UTF-8"));
		Data += ("&Shortcode=" + URLEncoder.encode("21527", "UTF-8"));
		Data += ("&SourceReference=" + URLEncoder.encode(SourceReference, "UTF-8"));
		Data += ("&MSISDN=" + URLEncoder.encode(MSISDN, "UTF-8"));
		Data += ("&Content=" + URLEncoder.encode(Content, "UTF-8"));
		Data += ("&DataType=" + URLEncoder.encode("0", "UTF-8"));

		Data += ("&Premium=" + URLEncoder.encode("1", "UTF-8"));
		Data += ("&CampaignID=" + URLEncoder.encode(CampaignId, "UTF-8"));
		Data += ("&username=" + URLEncoder.encode("FST", "UTF-8"));
		Data += ("&password=" + URLEncoder.encode("Fst@2017", "UTF-8"));

//		  $s_POST_DATA = "Channel=UK.VODAFONE"; // Channel
//		  $s_POST_DATA .= "&Shortcode=12345"; // Shortcode
//		  $s_POST_DATA .= "&SourceReference=3456"; // Source Reference
//		  $s_POST_DATA .= "&MSISDN=447811111111"; // Phone
//		  $s_POST_DATA .= "&Content=test"; // Content
//		  $s_POST_DATA .= "&DataType=0"; // Data Type
//		  $s_POST_DATA .= "&Premium=1"; // Premium
//		  $s_POST_DATA .= "&CampaignID=4321"; // CampaignID
		try {
			int Result = -1;

			URL myURL = new URL(RequestURL);
			HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
			String userCredentials = "FST:Fst@2017";
			// String basicAuth = "Basic " + new String(new
			// Base64().encode(userCredentials.getBytes()));
			byte[] bytesEncoded = Base64.encodeBase64(userCredentials.getBytes(), true);
			String authEncoded = new String(bytesEncoded);

			myURLConnection.setRequestProperty("Authorization", "Basic " + authEncoded);
			myURLConnection.setRequestMethod("POST");
			// myURLConnection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			myURLConnection.setRequestProperty("Content-Length", "" + Data.getBytes().length);
			String charset = "UTF-8";

			myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

			// myURLConnection.setRequestProperty("Content-Language", "en-US");
			myURLConnection.setUseCaches(false);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);

			// myURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT
			// 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0
			// Safari/537.36");

			DataOutputStream Output;
			Output = new DataOutputStream(myURLConnection.getOutputStream());
			Output.writeBytes(Data);
			Output.flush();
			Output.close();

			BufferedReader Input = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			StringBuffer ResponseBuffer = new StringBuffer();
			String InputLine;

			while ((InputLine = Input.readLine()) != null) {
				ResponseBuffer = ResponseBuffer.append(InputLine);
				ResponseBuffer = ResponseBuffer.append("\n\n\n");
			}
			System.out.print(ResponseBuffer);
			Response.replace(0, 0, ResponseBuffer.toString());
			String ResultCode = Response.substring(0, 4);
			Result = myURLConnection.getResponseCode();
			System.out.print("Result > " + Result);
			Input.close();

			return Result;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 99;
	}

	public static String SUPERVISORS = "SUPERVISORS";
	public static String HEAD_OF_DEPARTMENT = "HEAD_OF_DEPARTMENT";
	public static String MANAGING_DIRECTOR = "MANAGING_DIRECTOR";

	public static boolean checkifDateFormatOK(String date, String strformat) {
		// DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat format = new SimpleDateFormat(strformat);
		// Input to be parsed should strictly follow the defined date format
		// above.
		format.setLenient(false);

		try {
			format.parse(date);
			return true;

		} catch (ParseException e) {
			System.out.println("Date " + date + " is not valid according to " + ((SimpleDateFormat) format).toPattern()
					+ " pattern.");
			return false;
		}
	}
}