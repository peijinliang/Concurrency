package com.concurrency;

import com.concurrency.example.treadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 取出当前的session 进行处理
        // request.getSession().getAttribute("user");

        log.info("do filter ,{},{}", Thread.currentThread().getId(), request.getServletPath());

        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
