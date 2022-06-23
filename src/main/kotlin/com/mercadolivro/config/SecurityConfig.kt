package com.mercadolivro.config

import com.mercadolivro.enum.RoleEnum
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.AuthenticationFilter
import com.mercadolivro.security.AuthorizationFilter
import com.mercadolivro.security.JwtUtil
import com.mercadolivro.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class SecurityConfig(
    private val customerRepository: CustomerRepository,
    private val userDetails:UserDetailsCustomService,
    private val jwtUtil:JwtUtil
) : WebSecurityConfigurerAdapter(){

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customer"
    )

    private val ADMIN_MATCHERS = arrayOf(
        "/admin/**"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bcrypt())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST,*PUBLIC_POST_MATCHERS).permitAll()
            .antMatchers(*ADMIN_MATCHERS).hasAuthority(RoleEnum.ADMIN.description)
            .anyRequest().authenticated();
        http.addFilter(AuthenticationFilter(authenticationManager(),customerRepository,jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(),userDetails,jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bcrypt(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}