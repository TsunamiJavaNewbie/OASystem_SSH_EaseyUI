package com.tsunami.oa.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �ļ�����DateUtils.java ���ڴ�����ع�����
 */
public class DateUtils {

	/** ��-��-�� ʱ:��:�� ��ʾ��ʽ */
	// ��ע:���ʹ�ô�дHH��ʶʹ��24Сʱ��ʾ��ʽ,���ʹ��Сдhh�ͱ�ʾʹ��12Сʱ�Ƹ�ʽ��
	public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** ��-��-�� ��ʾ��ʽ */
	public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

	private static SimpleDateFormat simpleDateFormat;

	/**
	 * Date����תΪָ����ʽ��String����
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String DateToDatetime(Date source) {
		simpleDateFormat = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);
		if (null != source)
			return simpleDateFormat.format(source);
		else
			return null;
	}

	/**
	 * Date����תΪָ����ʽ��String����
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String DateToDate(Date source) {
		simpleDateFormat = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
		if (null != source)
			return simpleDateFormat.format(source);
		else
			return null;
	}

	/**
	 * Date����תΪָ����ʽ��String����
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String DateToString(Date source, String pattern) {
		if(null!=source){
			simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(source);
		}
		return null;
		
	}

	/**
	 * 
	 * unixʱ���תΪָ����ʽ��String����
	 * 
	 * 
	 * System.currentTimeMillis()��õ����Ǵ�1970��1��1�տ�ʼ�������ĺ�����
	 * unixʱ���:�Ǵ�1970��1��1�գ�UTC/GMT����ҹ����ʼ������������,����������
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String timeStampToString(long source, String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date(source * 1000);
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * �ַ���ת��Ϊ��Ӧ����(���ܻᱨ���쳣)
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String source, String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * ��õ�ǰʱ���Ӧ��ָ����ʽ
	 * 
	 * @param pattern
	 * @return
	 */
	public static String currentFormatDate(String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());

	}

	/**
	 * ��õ�ǰunixʱ���(��λ��)
	 * 
	 * @return ��ǰunixʱ���
	 */
	public static long currentTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}
}
