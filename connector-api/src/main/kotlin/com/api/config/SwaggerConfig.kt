package com.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * NAME : sungmin park
 * DATE : 2019-09-14
 */

@Configuration
@EnableSwagger2
class SwaggerConfig {
    companion object {
        const val API_VERSION = "1.0"
        const val NAME = "REDIS-CONNECTOR-API"
        const val AUTHOR = "sungmin park"
        const val AUTHOR_MAIL = "psmkab10@gmail.com"
    }

    @Bean
    fun docketConfig() : Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(
                ApiInfoBuilder()
                    .title(NAME)
                    .description(NAME)
                    .contact(Contact(AUTHOR, "", AUTHOR_MAIL))
                    .version(API_VERSION)
                    .build()
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.api.controller"))
            .paths(PathSelectors.any())
            .build()
    }
}