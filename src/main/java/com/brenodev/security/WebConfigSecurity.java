package com.brenodev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override // Configurar as solicitações de acesso por Http
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable() // Desativa as configurações padrão de memória do spring
		.authorizeRequests() // Permite restringir acesso
		.antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuário acessa a página inicial
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // permite qualquer usuário 
		.and().logout() // Mapeia a url de Logout e invalida o usuário autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // Encerra a sessão
	}
	
	@Override // Cria autenticação do usuário com o banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder()); // Validar a senha por texto sem padrão de encriptografia
	
	}
	
	
	@Override // Ignora URL específicas
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**"); // Ignora tudo que está dentro da página materialize
	}

}
