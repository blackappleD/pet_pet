package com.pet.pet.mg;

import com.pet.pet.core.CoreSource;
import com.pet.pet.core.po.UserPO;
import com.pet.pet.core.repo.UserRepo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackageClasses = UserPO.class)
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
@EnableJpaAuditing
public class MgApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(
				MgApplication.class,
				CoreSource.class
		).run(args);
	}
}