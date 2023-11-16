package com.djmobley7.json_parser;

import org.junit.Before;
import org.junit.Test;

public class JSONArray_Test {
	protected JSONArray jsonArray;
	
	@Before
	public void setUp() {
		jsonArray = new JSONArray();
	}
	
	@Test
	public void init_Test1() {
		String json = "[{\"one\": 2}, 3, \"four\"]";
		jsonArray.init(json);
		
		int size = jsonArray.size();
		for (int i = 0; i < size; ++i) {
			System.out.println(jsonArray.get(i));
		}
	}
}
