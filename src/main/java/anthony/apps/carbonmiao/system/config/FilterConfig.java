//package anthony.apps.carbonmiao.system.config;
//
//import anthony.apps.carbonmiao.system.filter.LoginFilter;
//import anthony.apps.carbonmiao.system.filter.LoginFilter2;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//
//
///**
// * 配置过滤器
// */
//@Configuration
//public class FilterConfig {
//
//    /**
//     * @return 返回过滤器
//     */
//    @Bean
//    public FilterRegistrationBean loginFilterRegistrationBean2() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setName("loginFilter2");
//        LoginFilter loginFilter = new LoginFilter();
//        registrationBean.setFilter(loginFilter);
//        registrationBean.setOrder(1);
//        ArrayList<String> urlList = new ArrayList<>();
//        urlList.add("/*");
//        registrationBean.setUrlPatterns(urlList);
//        return registrationBean;
//    }
//}
