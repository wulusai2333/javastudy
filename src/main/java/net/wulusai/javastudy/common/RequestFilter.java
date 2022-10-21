package net.wulusai.javastudy.common;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/account/balance", "/account/detail","/salary/create",
        "/salary/audit","/salary/detail","/preapprove/detail"}, filterName = "decryptFilter")

public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String remoteAddr = request.getRemoteAddr();
        //request.
    }

}
