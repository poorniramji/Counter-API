package com.eval.counter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
    private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;
	

	 @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  	
		 auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").and().withUser("wipro")
		 .password(passwordEncoder().encode("wipro")).roles("USER");
	       	
    }
	 
	 
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
    authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable();
        
        //antMatchers("/auth/*").hasAnyAuthority("ADMIN", "USER")
    }
    

 
    
    
   
    

    
}
