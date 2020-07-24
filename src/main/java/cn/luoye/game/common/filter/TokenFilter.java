package cn.luoye.game.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "tokenFilter")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("验证token");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //获取请求地址
        String requestURI = req.getRequestURI();
        String token = req.getHeader("token");
        log.info("请求地址:"+requestURI+",token="+token);


        if(!token.equals("baceda")){
            servletRequest.getRequestDispatcher("/error/token").forward(servletRequest, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("token验证完成");
    }
}