package com.example.testobject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping("/hello1")
	public String test() {
		return "hello,this is a springboot demo";
	}
}
