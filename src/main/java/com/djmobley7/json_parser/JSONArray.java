package com.djmobley7.json_parser;

import java.util.ArrayList;
import java.util.List;

public class JSONArray {
	List<String> valuesList;
	
	public JSONArray() {
		valuesList = new ArrayList<>();
	}
	
	public boolean init(String str) {
		valuesList.clear();
		
		if (str == null) {
			return false;
		}
		
		str = str.trim();
		if (str.length() < 2) {
			return false;
		}
		
		StringBuilder sb = new StringBuilder(str);
		if (sb.charAt(0) != JSONConstants.ARRAY_START ||
				sb.charAt(sb.length() -1) != JSONConstants.ARRAY_END) {
			return false;
		}
		
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		
		replaceCommas(sb);
		
		String[] values = sb.toString().split(String.valueOf(JSONConstants.ARRAY_DELIM));
		for (String value : values) {
			value = value.trim().replace(JSONConstants.SPECIAL_DELIM, JSONConstants.KEY_VALUE_PAIR_DELIM);
			valuesList.add(value);
		}
		
		return true;
	}
	
	public int size() {
		return valuesList.size();
	}
	
	public String get(int i) {
		if (i < size()) {
			return valuesList.get(i);
		}
		
		return null;
	}
	
	/**
	 * Replace commas in objects with special character
	 * @param sb
	 */
	protected void replaceCommas(StringBuilder sb) {
		boolean insideObject = false;
		
		for (int i = 0; i < sb.length(); ++i) {
			char c = sb.charAt(i);
			if (c == JSONConstants.OBJECT_START) {
				insideObject = true;
				continue;
			} else if (c == JSONConstants.OBJECT_END) {
				insideObject = false;
				continue;
			}
			
			if (insideObject) {
				if (c == JSONConstants.KEY_VALUE_PAIR_DELIM) {
					sb.setCharAt(i, JSONConstants.SPECIAL_DELIM);
				}
			}
		}
	}
}
