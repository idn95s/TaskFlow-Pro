package com.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void sampleTest() {
		int expected = 2137;
		int actual = 2135 + 1;
		assertThat(actual).isEqualTo(expected);
	}

}
