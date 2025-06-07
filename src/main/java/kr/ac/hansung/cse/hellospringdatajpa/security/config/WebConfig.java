package kr.ac.hansung.cse.hellospringdatajpa.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    //이걸로 컨트롤러 없이 뷰 등록하기 -> 주로 정적 페이지 할 때 많이 씀
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/admin/home").setViewName("admin_home");
        registry.addViewController("/accessDenied").setViewName("403");
        registry.addViewController("/login_success").setViewName("login_success");
    }

    //타임리프 연동용
    //이게 있어야 sec 네임스페이스 사용이 된다
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}