package com.ait.lms.config
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
//import org.springframework.security.oauth2.core.OAuth2Error
//import org.springframework.security.oauth2.core.OAuth2TokenValidator
//import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
//import org.springframework.security.oauth2.jwt.*
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher
//
//
//@EnableWebSecurity
//class SecurityConfig : WebSecurityConfigurerAdapter() {
//
//    @Value("\${auth0.audience}")
//    private val audience: String? = null
//
//    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private val issuer: String? = null
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//            .mvcMatchers("/api/member/login").permitAll()
//            .anyRequest().authenticated()
//            .and().oauth2Login()
//            .and().logout() // handle logout requests at /logout path
//            .logoutRequestMatcher(AntPathRequestMatcher("/api/logout")) // customize logout handler to log out of Auth0
//            .and().cors()
//            .and().oauth2ResourceServer().jwt()
//    }
//
//
//    @Bean
//    fun jwtDecoder(): JwtDecoder? {
//        val jwtDecoder: NimbusJwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoder
//        val audienceValidator = AudienceValidator(audience!!)
//        val withIssuer: OAuth2TokenValidator<Jwt>? = JwtValidators.createDefaultWithIssuer(issuer)
//        val withAudience: DelegatingOAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)
//        jwtDecoder.setJwtValidator(withAudience)
//        return jwtDecoder
//    }
//}
//
//class AudienceValidator(private val audience: String) : OAuth2TokenValidator<Jwt?> {
//    override fun validate(jwt: Jwt?): OAuth2TokenValidatorResult {
//        val error = OAuth2Error("invalid_token", "The required audience is missing", null)
//        return if (jwt!!.audience.contains(audience)) {
//            OAuth2TokenValidatorResult.success()
//        } else OAuth2TokenValidatorResult.failure(error)
//    }
//}