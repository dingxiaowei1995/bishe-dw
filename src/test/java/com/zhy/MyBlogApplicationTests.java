package com.zhy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

	@Test
	public void contextLoads() {
		
	}
	
	@Test 
	public void test1() {
		int i =1;
		int a= 2;
		 int s=i+a;
		 System.out.println(s);
	}

}
