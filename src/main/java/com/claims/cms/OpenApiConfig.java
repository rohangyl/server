//package com.claims.cms;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springdoc.core.GroupedOpenApi;
//import org.springdoc.core.SwaggerUiConfigParameters;
////import org.springdoc.webflux.ui.SwaggerUiWebfluxHandler;
////import org.springdoc.webflux.ui.SwaggerUiWebfluxHandler;
//
//
//@Configuration
////@EnableWebFlux
//public class OpenApiConfig {
//
//    @Bean
//    public GroupedOpenApi groupedOpenApi() {
//        return GroupedOpenApi.builder()
//            .group("api") // Specify the group name
//            .packagesToScan("com.claims.cms.controller") // Specify your controller package
//            .build();
//    }
//
////    @Bean
////    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
////        return new SwaggerUiConfigParameters();
////    }
////
////    @Bean
////    public org.springdoc.webflux.ui.SwaggerUiWebfluxHandler swaggerUiWebfluxHandler(SwaggerUiConfigParameters swaggerUiConfigParameters) {
////        return new org.springdoc.webflux.ui.SwaggerUiWebfluxHandler(swaggerUiConfigParameters);
////    }
//}
