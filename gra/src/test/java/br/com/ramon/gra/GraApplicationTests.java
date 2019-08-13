package br.com.ramon.gra;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ramon.gra.controller.MovieController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraApplicationTests {

	@Autowired
	private MovieController movieController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(movieController).isNotNull();
	}

}
