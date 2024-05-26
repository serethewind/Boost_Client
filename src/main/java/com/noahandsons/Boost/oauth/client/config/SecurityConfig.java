package com.noahandsons.Boost.oauth.client.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception{
//        http.authorizeExchange(authorize -> authorize.anyExchange().permitAll()).oauth2Login(Customizer.withDefaults()).oauth2Client(Customizer.withDefaults());
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("spring")
                .clientId("app")
                .clientSecret(passwordEncoder().encode("app-secret"))
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:8084/login/oauth2/code/{registrationId}")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .scope(OidcScopes.OPENID)
                .authorizationUri("http://localhost:8080/oauth2/authorize")
                .tokenUri("http://localhost:8080/oauth2/token")
                .issuerUri("http://localhost:8080")
//                .clientName()
                .build();


        InMemoryReactiveClientRegistrationRepository clientRegistrationRepository = new InMemoryReactiveClientRegistrationRepository(clientRegistration);
        return clientRegistrationRepository;
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager auth2AuthorizedClientManager
            (ReactiveClientRegistrationRepository clientRegistrationRepository,
             ServerOAuth2AuthorizedClientRepository auth2AuthorizedClientRepository)
    {
        ReactiveOAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .refreshToken()
                .clientCredentials()
                .build();

        DefaultReactiveOAuth2AuthorizedClientManager auth2AuthorizedClientManager = new DefaultReactiveOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
        auth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
        return auth2AuthorizedClientManager;
    }


}
