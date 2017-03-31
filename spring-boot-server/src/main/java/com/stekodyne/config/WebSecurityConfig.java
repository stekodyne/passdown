package com.stekodyne.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.ldap.base}")
    private String base;

    @Value("${spring.ldap.urls}")
    private String urls;

    @Value("${spring.ldap.username}")
    private String username;

    @Value("${spring.ldap.password}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .groupSearchBase("ou=groups")
                .userSearchFilter("uid={0}")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new Md5PasswordEncoder())
                .passwordAttribute("userPassword");
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        DefaultSpringSecurityContextSource defaultSpringSecurityContextSource =
                new DefaultSpringSecurityContextSource(Arrays.asList(urls), base);
        defaultSpringSecurityContextSource.setPassword(password);
        defaultSpringSecurityContextSource.setUserDn(username);
        defaultSpringSecurityContextSource.afterPropertiesSet();
        return defaultSpringSecurityContextSource;
    }

}
