package com.app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource)
	{
		return new JdbcUserDetailsManager(dataSource);
	}
	
    /*
    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        
        
        UserDetails user1 = User.builder()
                            .username("lalith")
                            .password("{noop}lalith123") 
                            .roles("EMPLOYEE")
                            .build();
        
        UserDetails user2 = User.builder()
                            .username("kishore")
                            .password("{noop}kishore123")
                            .roles("EMPLOYEE", "MANAGER")
                            .build();
        
        UserDetails user3 = User.builder()
                            .username("saran")
                            .password("{noop}saran123")
                            .roles("EMPLOYEE", "MANAGER","ADMIN")
                            .build();
        
        return new InMemoryUserDetailsManager(user1, user2, user3);
    } */
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    { 
        http.authorizeHttpRequests(c ->
                c
                .requestMatchers("/").hasRole("EMPLOYEE")
                .requestMatchers("/info/**").hasRole("MANAGER")
                .requestMatchers("/sys/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form ->
                form
                    .loginPage("/showMyLoginPage") 
                    .loginProcessingUrl("/authenticateTheUser") 
                    .permitAll() 
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(c -> 
            c.accessDeniedPage("/access-denied")
        );


        return http.build(); 
    }
}
