package pyrkap.logsandmetrics;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestCachingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authType = request.getAuthType();
        String headers = readAllHeaders(request).toString();
        MDC.put("auth-type", authType);
        MDC.put("headers", headers);
        LOGGER.info("REQUEST\t :" + readRequest(request.getInputStream()));
        MDC.clear();
        filterChain.doFilter(request, response);
    }

    private String readRequest(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }
    
    private Map<String, String> readAllHeaders(HttpServletRequest request) {
        List<String> headersNames = new ArrayList<>();
        request.getHeaderNames().asIterator().forEachRemaining(headersNames::add);
        return headersNames.stream()
                .collect(Collectors.toMap(header -> header, request::getHeader));
    }
}
