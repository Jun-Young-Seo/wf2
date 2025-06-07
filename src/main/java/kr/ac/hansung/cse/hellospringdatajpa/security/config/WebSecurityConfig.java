package kr.ac.hansung.cse.hellospringdatajpa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/about/**",
            "/contact/**",
            "/error/**",
            "/console/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(PUBLIC_MATCHERS).permitAll()
                        //아무나 접근가능 페이지
                        .requestMatchers("/", "/home", "/register", "/products").permitAll()

                        //어드민만 접근가능 페이지. admin/~~ 이런 식으로 오는 경우 모두 권한 검증
                        .requestMatchers("/admin/**","/products/new", "/products/save", "/products/edit/**", "/products/delete/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                //폼로그인 방식
                //Spring Security가 이 경우에는 자동으로 비밀번호 matches()를 내부적으로 호출한다
                //기존에 만들어오던 API Endpoint로 로그인 검증하던거랑 좀 다름
                //그래서 서비스 단에서 로그인 로직이 필요 없는 것
                //유저가 로그인 폼으로 요청 -> 내부 토큰 생성 -> CustomUserDetailsService가 DB랑 맞춰봄 -> 맞다면 login_success
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/login_success")
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                //익셉션 처리페이지
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/accessDenied")
                )
                //userDetailsService 구현체 붙이기
                .userDetailsService(customUserDetailsService);

        return http.build();
    }
}