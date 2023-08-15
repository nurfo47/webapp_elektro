package com.elektro.elektro.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.elektro.elektro.service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   
    @Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		var auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/posts/new").hasRole("ADMIN")
		.antMatchers("/posts/edit/**").hasRole("ADMIN")
		.antMatchers("/posts/delete/**").hasRole("ADMIN")
		.antMatchers("/professors/new").hasRole("ADMIN")
		.antMatchers("/professors/edit/**").hasRole("ADMIN")
		.antMatchers("/professors/delete/**").hasRole("ADMIN")
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/projects/edit/**").hasRole("ADMIN")
		.antMatchers("/projects/delete/**").hasRole("ADMIN")
		.antMatchers("/books/new").hasRole("ADMIN")
		.antMatchers("/books/delete/**").hasRole("ADMIN")
		.antMatchers("/users/").hasRole("ADMIN")
		.antMatchers("/users/delete/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
		.antMatchers("/registration**","/js/**", "/css/**", "/img/**").permitAll().anyRequest().authenticated()
                .and().formLogin(login -> login.loginPage("/login").permitAll()).logout(logout -> logout.invalidateHttpSession(true)
                .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll());

	}
	
}

