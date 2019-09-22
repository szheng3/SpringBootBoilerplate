package com.starter.demo;

import com.starter.demo.domain.User;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DemoApplicationTests {

//	@Autowired
//	private UserMapper userMapper;
//	@Test
//	public void testSelect() {
//		System.out.println(("----- selectAll method test ------"));
//		List<User> userList = userMapper.selectList(null);
//		Assert.assertEquals(5, userList.size());
//		userList.forEach(System.out::println);
//	}
//
//	@Test
//	public void testSelect2() {
//		System.out.println(("----- selectAll method test ------"));
//		List<User> userList = userMapper.findAllByAge(2);
//		Assert.assertEquals(1, userList.size());
//		userList.forEach(System.out::println);
//	}


	@Test
	public void contextLoads() {
	}

}
