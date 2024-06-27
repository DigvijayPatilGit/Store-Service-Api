package com.example.Store_app_Rest_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Store API",
                description = "CRUD operation for Store operations",
                summary = "This store api will add, delete, update and get the data",
                termsOfService = "terms and Conditions - Data retrival is only through ID",
                contact = @Contact(
                        name = "Digvijay Patil",
                        email = "dummy@email.com"
                ),
                license = @License(
                        name = "Digvijay Patil"
                ),
                version = "v1"

        ),
        servers = { //Use for accessing this apis on particular environment
                @Server(
                      description = "Dev",
                      url = "http://localhost:8090"
                ),
                @Server(
                        description = "Test",
                        url = "http://localhost:8090"
                )
        }
)
public class SwaggerConfig {
}
