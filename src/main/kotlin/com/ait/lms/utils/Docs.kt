package com.ait.lms.utils

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin


@CrossOrigin(origins = ["*"])
@Configuration
class Docs() {


    @Bean
    fun springShopOpenAPI(): OpenAPI? {
        val contact = Contact()
        contact.name = "Stephen Appah Boateng"
        contact.url = "https://lms.ait.edu.gh/"
        contact.email = "stephenappah@gmail.com"
        return OpenAPI()
            .info(
                Info().title("LMS Services [Learning Management System]")
                    .description("A REST Service Api for Learning Management System")
                    .contact(contact)
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("https://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("SpringShop Wiki Documentation")
                    .url("https://springshop.wiki.github.org/docs")
            )
    }
}