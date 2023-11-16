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
		if (!jsonObject.init(json)) {
			System.out.println("Unable to parse json");
		}
		
		String id = jsonObject.getValue("id");
		System.out.println("id = " + id);
		String name = jsonObject.getValue("name");
		System.out.println("name = " + name);
		String marks = jsonObject.getValue("marks");
		System.out.println("marks = " + marks);
	}
}
