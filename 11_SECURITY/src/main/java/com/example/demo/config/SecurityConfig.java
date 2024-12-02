package com.example.demo.config;

import com.example.demo.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.demo.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.demo.config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.demo.config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.demo.config.auth.logoutHandler.CustomLogoutHandler;
import com.example.demo.config.auth.logoutHandler.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //CSRF 비활성화
        http.csrf((config) -> {
            config.disable();
        });

        //권한체크
        http.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/","/join","/login").permitAll();
            auth.requestMatchers("/user").hasRole("USER");
            auth.requestMatchers("/member").hasRole("MEMBER");
            auth.requestMatchers("/admin").hasRole("ADMIN");
        });

        // 로그인
        http.formLogin((login) -> {
            login.permitAll();
            login.loginPage("/login");
            login.successHandler(new CustomLoginSuccessHandler());
            login.failureHandler(new CustomAuthenticationFailureHandler());
        });

        //로그아웃
        http.logout((logout) -> {
            logout.permitAll();
            logout.logoutUrl("/logout");
            logout.addLogoutHandler(new CustomLogoutHandler());
            logout.logoutSuccessHandler(new CustomLogoutSuccessHandler());
        });

        //예외처리
        http.exceptionHandling((exception) -> {
            exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
            exception.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        //로그아웃
        http.logout((logout) -> {
            logout.permitAll();
            logout.logoutUrl("/logout");
        });


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(
//                User.withUsername("user")
//                        .password(passwordEncoder
//                                .encode("1234"))
//                        .roles("USER")
//                        .build());
//
//        userDetailsManager.createUser(User.withUsername("member").password(passwordEncoder.encode("1234")).roles("MEMBER").build());
//
//        userDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN").build());
//
//        return userDetailsManager;
//    }

}
