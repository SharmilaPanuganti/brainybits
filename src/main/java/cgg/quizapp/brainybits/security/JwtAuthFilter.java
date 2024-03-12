package cgg.quizapp.brainybits.security;

import cgg.quizapp.brainybits.helpers.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JwtAuthFilter
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired
  JwtHelper jwtHelper;

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    // TODO Auto-generated method stub
    String requestToken = request.getHeader("Authorization");

    System.out.println(requestToken);

    String username = null;
    String token = null;

    //null and format
    if (requestToken != null && requestToken.startsWith("Bearer ")) {
      token = requestToken.substring(7);

      try {
        username = this.jwtHelper.getUsernameFromToken(token);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("Jwt token does not begin with Bearer");
    }

    //security
    if (
      username != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(
        username
      );
      if (this.jwtHelper.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );
        usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder
          .getContext()
          .setAuthentication(usernamePasswordAuthenticationToken);
      } else {
        System.out.println("Token is not validated...");
      }
    } else {
      System.out.println("username is null or context is not null");
    }
    filterChain.doFilter(request, response);
  }
}
