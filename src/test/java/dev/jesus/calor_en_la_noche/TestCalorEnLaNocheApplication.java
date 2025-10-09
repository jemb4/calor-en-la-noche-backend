package dev.jesus.calor_en_la_noche;

import org.springframework.boot.SpringApplication;

public class TestCalorEnLaNocheApplication {

	public static void main(String[] args) {
		SpringApplication.from(CalorEnLaNocheApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
