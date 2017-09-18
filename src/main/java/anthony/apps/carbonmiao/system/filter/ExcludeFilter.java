package anthony.apps.carbonmiao.system.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "excludeFilter", urlPatterns = "/*")
public class ExcludeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("ExcludeFilter");
        HttpServletRequest request1 = (HttpServletRequest) request;
        if (request1.getRequestURI().contains("111")) {
            request1.setAttribute("ok", "ok");
        } else {
            request1.setAttribute("ok", "not ok");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
