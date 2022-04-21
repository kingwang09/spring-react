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
import java.util.Arrays;

@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket restApi() {
        // 예외에 대한 Model 정의
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**")).build().pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .enableUrlTemplating(false).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(
                        new ResponseMessageBuilder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                                .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build()))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(
                        new ResponseMessageBuilder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                                .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build()))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(
                        new ResponseMessageBuilder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                                .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build()))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(
                        new ResponseMessageBuilder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                                .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build(),
                        new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build()))
                ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}
