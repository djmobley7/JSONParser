package com.djmobley7.json_parser;

import org.junit.Before;
import org.junit.Test;

public class JSONObject_Test {
	protected JSONObject jsonObject;
	
	@Before
	public void setUp() {
		jsonObject = new JSONObject();
	}
	@Test
	public void init_Test1() {
		String json = "{\"id\":1234,\"marks\":[{\"chemistry\":75,\"english\":85,\"physics\":80}],\"name\":\"user\"}";
		jsonObject.init(json);
	}
}
