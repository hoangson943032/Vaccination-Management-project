package qld.mock.vaccination.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import qld.mock.vaccination.service.Impl.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }
	
	protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsServiceImpl) // Cung cáp userservice cho spring security
            .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }
	
	@Bean
	SecurityFilterChain defaulSecurityFilterChain(HttpSecurity http) throws Exception {
		

		
		http
						.csrf().disable()
						.authorizeRequests()
						.antMatchers("/home", "/css/**", "/img/**", "/js/**").permitAll()
						.antMatchers("/vaccine-type/list").hasAnyAuthority("CUSTOMER", "ADMIN")	
						.antMatchers("/vaccine-manager/list").hasAnyAuthority("CUSTOMER", "ADMIN")				
						.antMatchers("/injection-schedule/list").hasAnyAuthority("CUSTOMER", "ADMIN")	
						.antMatchers("/news/list-news").hasAnyAuthority("CUSTOMER", "ADMIN")
						.antMatchers("/customer/update/**").hasAnyAuthority("CUSTOMER", "ADMIN")	
						
						.antMatchers("/customer/**").hasAuthority("ADMIN")
						.antMatchers("/employee/**").hasAuthority("ADMIN")
						.antMatchers("/vaccine-type/**").hasAuthority("ADMIN")
						.antMatchers("/vaccine-manager/**").hasAuthority("ADMIN")
						.antMatchers("/injection-schedule/**").hasAuthority("ADMIN")
						.antMatchers("/news/**").hasAuthority("ADMIN")
							
						
						.anyRequest().authenticated()
			            .and()
			            .formLogin()
			            .loginPage("/login")
			            .defaultSuccessUrl("/home")
			            .permitAll()
			            .and()
			            .logout()
			            .permitAll();
		
		return http.build();
		
		
	}
	
	
	

}
