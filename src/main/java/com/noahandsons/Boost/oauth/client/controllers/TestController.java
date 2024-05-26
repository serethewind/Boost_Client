//package com.noahandsons.Boost.oauth.client.controllers;
//
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping
//public class TestController {
//
////    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
//
//    @GetMapping("token")
//    public String token(){
//
////        return "Welcome";
//
//        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId("1").principal("client").build();
//
//       OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(request);
//
//       return client.getAccessToken().getTokenValue();
//    }
//}
