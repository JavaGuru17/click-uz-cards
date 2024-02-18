package uz.pdp.clickuzcards.security.filter;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.clickuzcards.proxy.TokenProxy;
import uz.pdp.clickuzcards.security.ClickUzAuthentication;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@NonNullApi
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final TokenProxy tokenProxy;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER)) {
            ResponseEntity<ClickUzAuthentication> verify = tokenProxy.verify(token.split(" ")[1]);
            if (verify.getBody() != null)
                SecurityContextHolder.getContext().setAuthentication(
                        ClickUzAuthentication.cast(verify.getBody())
                );
        }
        filterChain.doFilter(request, response);
    }
}
