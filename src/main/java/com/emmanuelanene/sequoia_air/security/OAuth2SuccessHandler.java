package com.emmanuelanene.sequoia_air.security;

import com.emmanuelanene.sequoia_air.entities.User;
import com.emmanuelanene.sequoia_air.enums.AuthMethod;
import com.emmanuelanene.sequoia_air.repo.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${frontend.success.url}")
    private String frontendUrl;

    private final UserRepo userRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        String email = defaultOAuth2User.getAttribute("email").toString();
        String name = defaultOAuth2User.getAttribute("name").toString();
        String picture = defaultOAuth2User.getAttribute("picture").toString();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setProfilePictureUrl(picture);
        user.setProvider(AuthMethod.GOOGLE);

        User user2 = userRepository.findByEmail(email).orElse(null);

        if (user2 == null) {
            userRepository.save(user);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user2 != null ? user2 : user);

        new DefaultRedirectStrategy().sendRedirect(request, response, frontendUrl);
    }
}