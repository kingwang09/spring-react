package io.labs.springreact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket restApi() {
        // 예외에 대한 Model 정의
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**")).build().pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .enableUrlTemplating(false).useDefaultResponseMessages(false);
    }


}
