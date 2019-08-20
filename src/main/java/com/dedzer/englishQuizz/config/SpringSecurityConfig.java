package com.dedzer.englishQuizz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().headers().frameOptions().disable()
                .and().formLogin().loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .defaultSuccessUrl("/index")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .roles("USER")
                .and()
                .withUser("admin")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .roles("ADMIN")
                .and()
                .passwordEncoder(bCryptPasswordEncoder);
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.login, u.password, 1 from user u\" + \" where u.login=?")
                .authoritiesByUsernameQuery("SELECT u.login, u.role, 1 from user u\" + \" where u.login=?")
                .dataSource(jdbcTemplate.getDataSource())
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
