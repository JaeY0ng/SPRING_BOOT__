package com.example.demo.config.auth;

import com.example.demo.domain.dto.UserDto;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsOauth2ServiceImpl extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("PrincipalDetailsOauth2ServiceImpl's loadUser ..." + userRequest);
        System.out.println("userRequest.getClientRegistration() :"+ userRequest.getClientRegistration());
        System.out.println("userRequest.getAccessToken() : "+ userRequest.getAccessToken());
        System.out.println("userRequest.getAdditionalParameters() : "+ userRequest.getAdditionalParameters());
        System.out.println("userRequest.getAccessToken().getTokenValue() : "+ userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest.getAccessToken().getTokenType().getValue() : "+ userRequest.getAccessToken().getTokenType().getValue());
        System.out.println("userRequest.getAccessToken().getScopes() : "+ userRequest.getAccessToken().getScopes());

        //OAuth2UserInfo
        OAuth2User oAuthUser = super.loadUser(userRequest);
        System.out.println();
        System.out.println("oAuthUser : " + oAuthUser);


        UserDto userDto = new UserDto();
        userDto.setUsername("ABCD");
        userDto.setRole("ROLE_USER");
        PrincipalDetails principalDetails = new PrincipalDetails();
        principalDetails.setUserDto(userDto);                       //변경예정
        principalDetails.setAttributes(oAuthUser.getAttributes());  //변경예정
        principalDetails.setAccessToken(userRequest.getAccessToken().getTokenValue());
        return principalDetails;
    }
}