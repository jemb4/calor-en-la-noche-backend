package dev.jesus.calor_en_la_noche;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CalorEnLaNocheApplicationTests {

	@Test
	void contextLoads() {
	}

}
