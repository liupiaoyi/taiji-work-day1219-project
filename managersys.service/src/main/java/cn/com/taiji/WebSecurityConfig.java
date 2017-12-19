package cn.com.taiji;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").
		and().withUser("root").password("root").roles("USER").
		and().withUser("taiji").password("taiji").roles("ADMIN");
		
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
		.and().formLogin()
		.and().httpBasic();
	//自定义页面

		http.csrf().disable();
//		http.authorizeRequests()
//			.antMatchers("/webjars/**", "/signup", "/about").permitAll()
////			.antMatchers("/admin/").hasAnyRole("ROLE_ADMIN","ROLE_USER")
////			.antMatchers("/admin/**").hasRole("USER")
//			.anyRequest().authenticated()
//		.and().formLogin().loginPage("/login").permitAll()	 //.loginPage("/login") 传一个跳转请求“/login” 到controller
//									            //.and().httpBasic();
//			.and().logout().logoutSuccessUrl("/login").permitAll()
//			;		
//		
//		
	}

}
