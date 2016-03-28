package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuyueqiu on 16-3-15.
 */
public class LoginInterceptor implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("preHandle");
        //System.out.println(httpServletRequest.getRequestURL());
        System.out.println(httpServletRequest.getRequestURI());

//        if(httpServletRequest.getSession().getAttribute("id") == null){
//            if(httpServletRequest.getRequestURI().equals("/login")
//                    || httpServletRequest.getRequestURI().equals("/oauth")
//                    || httpServletRequest.getRequestURI().equals("/register")){
//                return true;
//            }
//            httpServletResponse.setHeader("Content-Type", "text/plain; charset=utf-8");
//            httpServletResponse.getWriter().write("您还未登录，请登录！");
//            httpServletResponse.getWriter().close();
//            return false;
//        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //System.out.println("postHandle");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //System.out.println("afterCompletion");
    }
}
