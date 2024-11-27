package com.example.demo.C05Naver;

import com.example.demo.C04Kakao.C02KakaoLoginController;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
@RequestMapping("/naver")
public class C01NaverLoginController {

    private final String redirectUrl = "http://localhost:8090/naver/login";
    private final String encodedUrl = URLEncoder.encode(redirectUrl, StandardCharsets.UTF_8.toString());
    private final String logoutUrl = "https://nid.naver.com/nidlogin.logout?returl=" + encodedUrl;

    private final String CLIENT_ID ="FLGUS7TEn0fSWnTAKFZB";
    private final String CLIENT_SECRET="bkH6VUSIGV";
    private final String CALLBACK_URL ="http://localhost:8090/naver/callback";
    private NaverLoginResponse naverLoginResponse;

    public C01NaverLoginController() throws UnsupportedEncodingException {
    }


    @GetMapping("/login")
    public String login_post(){
        log.info("GET /naver/login...");
        return "redirect:https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+CLIENT_ID+"&state=STATE_STRING&redirect_uri="+CALLBACK_URL;
    }
    @GetMapping("/callback")
    @ResponseBody
    public void callback(@RequestParam("code") String code, @RequestParam("state") String state){
        log.info("GET /naver/callback... code : " + code + " state : " + state);

        String url = "https://nid.naver.com/oauth2.0/token";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("client_secret",CLIENT_SECRET);
        params.add("code",code);
        params.add("state",state);

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NaverLoginResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, NaverLoginResponse.class);
        System.out.println(response.getBody());

        this.naverLoginResponse = response.getBody();

    }

    @GetMapping("/unlink")
    public String unlink(){
        log.info("GET /naver/unlink...");
        return "redirect:https://nid.naver.com/oauth2.0/logout?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&access_toke=" + naverLoginResponse.getAccess_token();
    }

    @GetMapping("/disconnect")
    public String disconnect(){
        log.info("GET /naver/disconnect");
        return "redirect:" + logoutUrl;
    }

    @Data
    private static class NaverLoginResponse {
        @JsonProperty(value = "access_token")
        public String access_token;
        public String refresh_token;
        public String token_type;
        public String expires_in;
    }

}
