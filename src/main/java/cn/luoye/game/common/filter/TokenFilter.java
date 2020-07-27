package cn.luoye.game.common.filter;

import cn.luoye.game.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "tokenFilter")
@Slf4j
public class TokenFilter implements Filter {

    @Autowired
    RedisUtil redisUtil;
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
        if(requestURI.equals("/userInfo/register")||requestURI.equals("/userInfo/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            if(null==token||token.equals("")){
                servletRequest.getRequestDispatcher("/error/token").forward(servletRequest, servletResponse);
            }else{
                try {
                    String[] array = token.split(" ");
                    String username = array[0];
                    if(redisUtil.hasKey(username+"token")){
                        if(!redisUtil.get(username+"token").equals(token)){
                            servletRequest.getRequestDispatcher("/error/token").forward(servletRequest, servletResponse);
                        }else{
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    }else{
                        servletRequest.getRequestDispatcher("/error/token").forward(servletRequest, servletResponse);
                    }
                }catch (Exception e){
                    log.info("token传参异常");
                    servletRequest.getRequestDispatcher("/error/token").forward(servletRequest, servletResponse);
                }

            }
        }
    }

    @Override
    public void destroy() {
        log.info("token验证完成");
    }
}