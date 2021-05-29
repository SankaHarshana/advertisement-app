package com.example.advertisementapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.GET, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/login-form").permitAll()
                .antMatchers(HttpMethod.POST, "/user-login").permitAll()
                .antMatchers(HttpMethod.GET, "/dashboard").permitAll()
                .antMatchers(HttpMethod.GET, "/dashboard/get-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/dashboard/update-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/dashboard/update-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/dashboard/delete-by-id/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/dashboard/create").permitAll()
                .and().formLogin().loginPage("/login-form").permitAll();
        super.configure(http);
    }
}
