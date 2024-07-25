package br.edu.unoesc.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface SecurityConfigs extends ImportAware, BeanClassLoaderAware {
    @Bean
    PasswordEncoder passwordEncoder();

    void configure(HttpSecurity http) throws Exception;

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception;
}
