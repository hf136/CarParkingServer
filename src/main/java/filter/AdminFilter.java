package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wyq on 2016/4/1.
 */
public class AdminFilter  implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {
        String testParam = filterConfig.getInitParameter("test-param");
        System.out.println("test-param: " + testParam);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //System.out.println(request.getRequestURI());
        if(request.getSession().getAttribute("puserid") == null){
            if(!request.getRequestURI().equals("/admin/signin.html") && !request.getRequestURI().equals("/admin/signup.html")) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect("/admin/signin.html");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
