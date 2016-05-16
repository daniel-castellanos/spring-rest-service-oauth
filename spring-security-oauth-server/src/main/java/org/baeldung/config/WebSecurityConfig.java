package org.baeldung.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        /*auth.inMemoryAuthentication().
            withUser("john").password("123").roles("USER").
            and().
            withUser("tom").password("111").roles("ADMIN");*/
    	
    	auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select username, password, enabled from oauth_users where username=?")
			.authoritiesByUsernameQuery("select username, role from oauth_user_roles where username=?");
			;
    	
        // @formatter:on
    }

    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            ;
        // @formatter:on
    }

    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
