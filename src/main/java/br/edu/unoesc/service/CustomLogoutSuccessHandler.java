package br.edu.unoesc.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
    public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }

        response.addCookie(createClearingCookie("JSESSIONID", "/"));

        // Redirecionar para a página de login com mensagem de sucesso
        response.sendRedirect(request.getContextPath() + "/login?logout");
    }

    private jakarta.servlet.http.Cookie createClearingCookie(String name, String path) {
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie(name, "");
        cookie.setPath(path);
        cookie.setMaxAge(0);
        return cookie;
    }


}
