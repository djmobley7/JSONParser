package com.djmobley7.json_parser;

import java.util.HashMap;
import java.util.Map;

public class JSONObject {
	public Map<String, String> keyValuePairsMap;
	
	public JSONObject() {
		keyValuePairsMap = new HashMap<>();
	}
	
	public boolean init(String str) {
		keyValuePairsMap.clear();
		
		if (str == null) {
			return false;
		}
		
		str = str.trim();
		if (str.length() < 2) {
			return false;
		}
		
		StringBuilder sb = new StringBuilder(str);
		
		if (sb.charAt(0) != JSONConstants.OBJECT_START ||
				sb.charAt(sb.length() - 1) != JSONConstants.OBJECT_END) {
			return false;
		}
		
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		
		// temporarily replace all commas inside arrays so we can easily get key-value pairs
		replaceCommas(sb);
		
		String[] keyValuePairs = sb.toString().split(String.valueOf(JSONConstants.KEY_VALUE_PAIR_DELIM));
		
		for (String keyValuePair : keyValuePairs) {
			keyValuePair = keyValuePair.trim();
			String[] keyValue = keyValuePair.split(String.valueOf(JSONConstants.KEY_VALUE_DELIM), 2);
			if (keyValue.length != 2) {
				// malformed
				return false;
			}
			
			String key = keyValue[0].trim();
			if (key.length() < 2) {
				return false;
			}
			
			if (!key.startsWith("\"") ||
					!key.endsWith("\"")) {
				return false;
			}
					
			key = key.substring(1, key.length() - 1);
			if (key.length() == 0) {
				// no key value
				return false;
			}
			
			String value = keyValue[1].trim();
			
			// put commas back before saving
			value = value.replace(String.valueOf(JSONConstants.SPECIAL_DELIM), 
					String.valueOf(JSONConstants.ARRAY_DELIM));
			
			if (keyValuePairsMap.put(key, value) != null) {
				// duplicate key at this level
				return false;
			}
		}

		return true;
	}
	
	public String getValue(String key) {
		return keyValuePairsMap.get(key);
	}
	
	/**
	 * Replace commons in arrays with special character
	 * @param sb
	 */
	protected void replaceCommas(StringBuilder sb) {
		boolean insideArray = false;
		
		for (int i = 0; i < sb.length(); ++i) {
			char c = sb.charAt(i);
			if (c == JSONConstants.ARRAY_START) {
				insideArray = true;
				continue;
			} else if (c == JSONConstants.ARRAY_END) {
				insideArray = false;
				continue;
			}
			
			if (insideArray) {
				if (c == JSONConstants.ARRAY_DELIM) {
					sb.setCharAt(i, JSONConstants.SPECIAL_DELIM);
				}
			}
		}
	}
}
