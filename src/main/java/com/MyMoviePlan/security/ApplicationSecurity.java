package com.MyMoviePlan.security;

import com.MyMoviePlan.filter.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Component
public class ApplicationSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().fullyAuthenticated())
                .httpBasic(basic -> {
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // @Bean
    // protected AuthenticationManager authenticationManager() throws Exception {
    // return super.authenticationManager();
    // }

    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider authenticationProvider) {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

}

// package com.MyMoviePlan.security;

// import com.MyMoviePlan.filter.JWTFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.builders.WebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import
// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity(prePostEnabled = true)
// public class ApplicationSecurity extends WebSecurityConfigurerAdapter{

// @Autowired
// private ApplicationUserDetailsService userDetailsService;

// @Autowired
// private PasswordEncoder passwordEncoder;

// @Autowired
// private JWTFilter jwtFilter;

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http.headers().frameOptions().sameOrigin();
// http.csrf().disable().cors().disable()
// .cors().configurationSource(corsConfigurationSource())
// .and()
// .authorizeRequests()
// .antMatchers(HttpMethod.OPTIONS, "/**")
// .permitAll()
// .anyRequest()
// .fullyAuthenticated()
// .and()
// .httpBasic()
// .and()
// .sessionManagement()
// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
// }

// @Override
// public void configure(WebSecurity web) throws Exception {
// web.ignoring()
// .antMatchers("/h2-console/**", "/auditorium/**", "/movie/**", "/show/**",
// "/user/**",
// "/user/forgot-password", "/user/authenticate", "/movie-show/**",
// "/booking/**", "/logout");
// }

// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// auth.authenticationProvider(authenticationProvider());
// }

// @Bean
// public DaoAuthenticationProvider authenticationProvider() {
// DaoAuthenticationProvider authenticationProvider = new
// DaoAuthenticationProvider();
// authenticationProvider.setPasswordEncoder(passwordEncoder);
// authenticationProvider.setUserDetailsService(userDetailsService);
// return authenticationProvider;
// }

// @Bean
// CorsConfigurationSource corsConfigurationSource() {
// CorsConfiguration configuration = new CorsConfiguration();
// configuration.setAllowedOrigins(Arrays.asList("*"));
// configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
// "DELETE", "OPTIONS"));
// configuration.setAllowCredentials(true);
// // the below three lines will add the relevant CORS response headers
// configuration.addAllowedOrigin("*");
// configuration.addAllowedHeader("*");
// configuration.addAllowedMethod("*");
// UrlBasedCorsConfigurationSource source = new
// UrlBasedCorsConfigurationSource();
// source.registerCorsConfiguration("/**", configuration);
// return source;
// }

// @Override
// @Bean
// protected AuthenticationManager authenticationManager() throws Exception {
// return super.authenticationManager();
// }
// }
