package anthony.apps.carbonmiao.system.config;

import anthony.apps.carbonmiao.system.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * Created by CHENDONG239 on 2017-04-25.
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean greetingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter");
        LoginFilter loginFilter = new LoginFilter();
        registrationBean.setFilter(loginFilter);
        registrationBean.setOrder(1);
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }
}
